package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GGRegistry;
import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntity;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class GGEntities {

    // Port a Forge: EntityType.Builder en lugar de FabricEntityTypeBuilder. El Builder de vanilla solo
    // tiene sized() (escalable); el original usaba fixed() para el aneuploidian, que no tiene crias, asi que da igual.
    public static final EntityType<GippleEntity> GIPPLE = GGRegistry.register(
            Registries.ENTITY_TYPE,
            "gipple",
            EntityType.Builder.of(GippleEntity::new, MobCategory.CREATURE).sized(0.75f, 0.5f).build("gipple")
    );
    public static final EntityType<AneuploidianEntity> ANEUPLOIDIAN = GGRegistry.register(
            Registries.ENTITY_TYPE,
            "aneuploidian",
            EntityType.Builder.of(AneuploidianEntity::new, MobCategory.MONSTER).sized(1.5f, 1.7f).build("aneuploidian")
    );
    public static void init(){
    }

    // Port a Forge: reemplaza FabricDefaultAttributeRegistry.
    public static void onAttributes(EntityAttributeCreationEvent event){
        event.put(GIPPLE, GippleEntity.createGippleAttributes().build());
        event.put(ANEUPLOIDIAN, AneuploidianEntity.createSomethingAttributes().build());
    }
}
