package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;

public class GGBannerPatterns {

    public static void createPattern(String name, ResourceKey<BannerPattern> key){
        GGRegistry.register(Registries.BANNER_PATTERN, key.location(), new BannerPattern(name));
    }
    public static final ResourceKey<BannerPattern> GIPPLE = register("gipple");

    private static ResourceKey<BannerPattern> register(String id) {
        return ResourceKey.create(Registries.BANNER_PATTERN, new ResourceLocation(GipplesGalore.MOD_ID, id));
    }

    public static void init(){
        createPattern("gipple", GIPPLE);
    }
}
