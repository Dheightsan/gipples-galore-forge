package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class GGPaintings {

    public static final PaintingVariant GIPPLE = of("gipple");

    public static void init() {

    }

    private static PaintingVariant of(String id) {
        return GGRegistry.register(Registries.PAINTING_VARIANT, new ResourceLocation(GipplesGalore.MOD_ID, id), new PaintingVariant(32, 32));
    }
}
