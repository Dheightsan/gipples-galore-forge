package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class GGBiomeTags {
    public static final TagKey<Biome> EXTRA_GIPPLE_HABITATS = of("extra_gipple_habitats");

    private static TagKey<Biome> of(String id) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(GipplesGalore.MOD_ID, id));
    }
}
