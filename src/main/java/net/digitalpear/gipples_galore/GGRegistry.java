package net.digitalpear.gipples_galore;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegisterEvent;

// Port a Forge: el original llama a Registry.register en inicializadores estaticos, que en Forge
// falla porque los registros vanilla estan congelados. Los objetos se siguen creando igual que en
// el original (asi el resto del codigo no cambia), pero aqui solo se anotan, y se registran de
// verdad en el RegisterEvent de su registro.
public final class GGRegistry {
   private record Entry<T>(ResourceKey<? extends Registry<T>> registry, ResourceLocation id, T value) {
   }

   private static final List<Entry<?>> PENDING = new ArrayList<>();

   private GGRegistry() {
   }

   public static <V, T extends V> T register(ResourceKey<? extends Registry<V>> registry, ResourceLocation id, T value) {
      PENDING.add(new Entry<V>(registry, id, value));
      return value;
   }

   public static <V, T extends V> T register(ResourceKey<? extends Registry<V>> registry, String path, T value) {
      return register(registry, new ResourceLocation(GipplesGalore.MOD_ID, path), value);
   }

   // El original pide el id con BuiltInRegistries.X.getKey(obj) mientras crea objetos; antes del
   // RegisterEvent eso devuelve el id por defecto (minecraft:air), asi que el id sale de lo anotado.
   public static ResourceLocation idOf(Object value) {
      for (Entry<?> entry : PENDING) {
         if (entry.value() == value) {
            return entry.id();
         }
      }
      throw new IllegalStateException("Objeto sin anotar en GGRegistry: " + value);
   }

   // Forge congela los registros al construir el mod, y Block/Item/EntityType piden su holder al
   // crearse. Por eso los init() del original se ejecutan aqui, en el primer RegisterEvent, cuando
   // Forge ya abrio todos los registros.
   private static Runnable bootstrap;

   public static void setBootstrap(Runnable runnable) {
      bootstrap = runnable;
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public static void onRegister(RegisterEvent event) {
      if (bootstrap != null) {
         Runnable run = bootstrap;
         bootstrap = null;
         run.run();
      }
      for (Entry<?> entry : PENDING) {
         if (event.getRegistryKey().equals(entry.registry())) {
            Entry e = entry;
            event.register((ResourceKey) e.registry(), helper -> helper.register(e.id(), e.value()));
         }
      }
   }
}
