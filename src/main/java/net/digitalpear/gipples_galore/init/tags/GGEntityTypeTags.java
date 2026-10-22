package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class GGEntityTypeTags {
    public static final TagKey<EntityType<?>> ANEUPLOIDIAN_TARGET_BLACKLIST = of("aneuploidian_target_blacklist");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(GipplesGalore.MOD_ID, id));
    }
}
