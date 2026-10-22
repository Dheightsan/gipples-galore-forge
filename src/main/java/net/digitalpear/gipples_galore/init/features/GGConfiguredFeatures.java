package net.digitalpear.gipples_galore.init.features;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.common.features.GGFeatures;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import java.util.ArrayList;
import java.util.List;

public class GGConfiguredFeatures {

    public static List<ResourceKey<ConfiguredFeature<?, ?>>> configuredFeatures = new ArrayList<>();
    public static ResourceKey<ConfiguredFeature<?, ?>> of(String id) {
        ResourceKey<ConfiguredFeature<?, ?>> placed = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(GipplesGalore.MOD_ID, id));
        configuredFeatures.add(placed);
        return placed;
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY_VEGETATION = of("gipple_colony_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY = of("gipple_colony");




    public static final SimpleWeightedRandomList.Builder GELATITE_VEGETATION_PROVIDER = SimpleWeightedRandomList.<BlockState>builder()
            .add(Blocks.AIR.defaultBlockState(), 30)
            .add(Blocks.GLOW_LICHEN.defaultBlockState()
                    .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), true)
                    .setValue(MultifaceBlock.getFaceProperty(Direction.UP), false)
                    .setValue(MultifaceBlock.getFaceProperty(Direction.NORTH), false)
                    .setValue(MultifaceBlock.getFaceProperty(Direction.SOUTH), false)
                    .setValue(MultifaceBlock.getFaceProperty(Direction.EAST), false)
                    .setValue(MultifaceBlock.getFaceProperty(Direction.WEST), false), 30);



    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.lookup(Registries.CONFIGURED_FEATURE);

        for (int i = 1; i< 8; i++){
            GELATITE_VEGETATION_PROVIDER.add(GGBlocks.GELATIN_LAYER.defaultBlockState().setValue(GelatinLayerBlock.LAYERS, i), 9 - i);
        }

        FeatureUtils.register(featureRegisterable, GIPPLE_COLONY, GGFeatures.GIPPLE_COLONY, new VegetationPatchConfiguration(GGBlockTags.GIPPLE_COLONY_REPLACEABLE, BlockStateProvider.simple(GGBlocks.GELATITE), PlacementUtils.inlinePlaced(registryEntryLookup.getOrThrow(GIPPLE_COLONY_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.2F, UniformInt.of(6, 9), 0.7F));

        FeatureUtils.register(featureRegisterable, GIPPLE_COLONY_VEGETATION, GGFeatures.GELATITE_VEGETATION, new NetherForestVegetationConfig(new WeightedStateProvider(GELATITE_VEGETATION_PROVIDER.build()), 8, 5));
    }
}
