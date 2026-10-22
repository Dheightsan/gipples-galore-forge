package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class GGBannerPatternItemTags {
    public static final TagKey<BannerPattern> GIPPLE_PATTERN_ITEM = of("pattern_item/gipple");


    private static TagKey<BannerPattern> of(String id) {
        return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(GipplesGalore.MOD_ID, id));
    }
}
