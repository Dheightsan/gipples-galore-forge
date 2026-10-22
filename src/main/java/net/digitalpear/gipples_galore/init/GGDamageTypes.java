package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class GGDamageTypes {
    public static final ResourceKey<DamageType> GIPPLE_EFFECT = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(GipplesGalore.MOD_ID, "gipple_effect"));

    public static void bootstrap(BootstapContext<DamageType> damageTypeRegisterable) {
        damageTypeRegisterable.register(GIPPLE_EFFECT, new DamageType("gippleEffect", DamageScaling.NEVER, 0.0F));
    }


    public static DamageSource gippleEffect(Level world) {
        return new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(GIPPLE_EFFECT));
    }
}
