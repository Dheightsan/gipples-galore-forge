package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class GGBlockTags {

    public static final TagKey<Block> JELLIES = of("jellies");
    public static final TagKey<Block> GIPPLE_FOOD = of("gipple_food");
    public static final TagKey<Block> GIPPLE_COLONY_REPLACEABLE = of("gipple_colony_replaceable");
    public static final TagKey<Block> GELATINOUS_GROWTH_SUPPORTING = of("gelatinous_growth_supporting");

    private static TagKey<Block> of(String id) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(GipplesGalore.MOD_ID, id));
    }


}
