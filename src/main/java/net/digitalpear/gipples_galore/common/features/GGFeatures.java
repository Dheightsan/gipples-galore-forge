package net.digitalpear.gipples_galore.common.features;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

public class GGFeatures {

    public static final Feature<NetherForestVegetationConfig> GELATITE_VEGETATION = register("gelatite_vegetation", new GelatiteVegetationFeature(NetherForestVegetationConfig.CODEC));
    public static final Feature<VegetationPatchConfiguration> GIPPLE_COLONY = register("gipple_colony", new GippleColonyFeature(VegetationPatchConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return GGRegistry.register(Registries.FEATURE, new ResourceLocation(GipplesGalore.MOD_ID, name), feature);
    }


    public static void init() {
    }
}
