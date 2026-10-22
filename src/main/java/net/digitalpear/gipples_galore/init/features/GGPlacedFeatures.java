package net.digitalpear.gipples_galore.init.features;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.tags.GGBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.ArrayList;
import java.util.List;

public class GGPlacedFeatures {

    public static List<ResourceKey<PlacedFeature>> placedFeatures = new ArrayList<>();
    public static ResourceKey<PlacedFeature> of(String id) {
        ResourceKey<PlacedFeature> placed = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(GipplesGalore.MOD_ID, id));
        placedFeatures.add(placed);
        return placed;
    }
    public static final ResourceKey<PlacedFeature> GIPPLE_COLONY = of("gipple_colony");
    public static final ResourceKey<PlacedFeature> GIPPLE_COLONY_COMMON = of("gipple_colony_common");



    public static void bootstrap(BootstapContext<PlacedFeature> featureRegisterable) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> gippleColony = registryEntryLookup.getOrThrow(GGConfiguredFeatures.GIPPLE_COLONY);

        PlacementUtils.register(featureRegisterable, GIPPLE_COLONY, gippleColony,
                RarityFilter.onAverageOnceEvery(60),
                CountPlacement.of(12),
                HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.absolute(12), 1)),
                InSquarePlacement.spread(), BiomeFilter.biome());

        PlacementUtils.register(featureRegisterable, GIPPLE_COLONY_COMMON, gippleColony,
                RarityFilter.onAverageOnceEvery(10),
                CountPlacement.of(20),
                HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.absolute(12), 1)),
                InSquarePlacement.spread(), BiomeFilter.biome());
    }


    // Port a Forge: la aparicion en biomas la hacen los biome modifiers de
    // data/gipples_galore/forge/biome_modifier/ (mismo paso FLUID_SPRINGS y mismos biomas).
    public static void init(){
    }
}
