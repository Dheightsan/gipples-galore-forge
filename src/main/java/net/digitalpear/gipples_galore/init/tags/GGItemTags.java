package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GGItemTags {
    public static final TagKey<Item> GIPPLE_FOOD = of("gipple_food");

    private static TagKey<Item> of(String id) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(GipplesGalore.MOD_ID, id));
    }

}
