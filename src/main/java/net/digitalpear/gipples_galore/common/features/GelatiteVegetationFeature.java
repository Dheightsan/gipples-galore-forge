package net.digitalpear.gipples_galore.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;

public class GelatiteVegetationFeature extends Feature<NetherForestVegetationConfig> {
    public GelatiteVegetationFeature(Codec<NetherForestVegetationConfig> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NetherForestVegetationConfig> context) {
        WorldGenLevel structureWorldAccess = context.level();
        BlockPos blockPos = context.origin();
        NetherForestVegetationConfig netherForestVegetationFeatureConfig = context.config();
        RandomSource random = context.random();

        int i = blockPos.getY();
        if (i >= structureWorldAccess.getMinBuildHeight() + 1 && i + 1 < structureWorldAccess.getMaxBuildHeight()) {
            int j = 0;

            for(int spreadLoop = 0; spreadLoop < netherForestVegetationFeatureConfig.spreadWidth * netherForestVegetationFeatureConfig.spreadWidth; ++spreadLoop) {
                BlockPos blockPos2 = blockPos.offset(random.nextInt(netherForestVegetationFeatureConfig.spreadWidth) - random.nextInt(netherForestVegetationFeatureConfig.spreadWidth), random.nextInt(netherForestVegetationFeatureConfig.spreadHeight) - random.nextInt(netherForestVegetationFeatureConfig.spreadHeight), random.nextInt(netherForestVegetationFeatureConfig.spreadWidth) - random.nextInt(netherForestVegetationFeatureConfig.spreadWidth));
                BlockState blockState2 = netherForestVegetationFeatureConfig.stateProvider.getState(random, blockPos2);
                if (structureWorldAccess.isEmptyBlock(blockPos2) && blockPos2.getY() > structureWorldAccess.getMinBuildHeight() && blockState2.canSurvive(structureWorldAccess, blockPos2)) {
                    if (blockState2.getBlock() instanceof DoublePlantBlock){
                        if (structureWorldAccess.isEmptyBlock(blockPos2.above())){
                            DoublePlantBlock.placeAt(structureWorldAccess, blockState2, blockPos2, 2);
                            ++j;
                        }
                    }
                    else{
                        structureWorldAccess.setBlock(blockPos2, blockState2, 2);
                        ++j;
                    }

                }
                else if (structureWorldAccess.getBlockState(blockPos2).is(Blocks.WATER)
                        && blockPos2.getY() > structureWorldAccess.getMinBuildHeight()
                        && blockState2.canSurvive(structureWorldAccess, blockPos2)){
                    structureWorldAccess.setBlock(blockPos2, Blocks.SEAGRASS.defaultBlockState(), 2);
                    ++j;
                }
                else if(structureWorldAccess.getBlockState(blockPos2).is(GGBlocks.GELATIN_LAYER)
                        && (structureWorldAccess.getBlockState(blockPos2).getValue(GelatinLayerBlock.LAYERS) < 8)
                        && blockPos2.getY() > structureWorldAccess.getMinBuildHeight()
                        && blockState2.canSurvive(structureWorldAccess, blockPos2)){
                    structureWorldAccess.setBlock(blockPos2, GGBlocks.GELATIN_LAYER.defaultBlockState()
                            .setValue(GelatinLayerBlock.LAYERS, structureWorldAccess.getBlockState(blockPos2).getValue(GelatinLayerBlock.LAYERS) + 1)
                            .setValue(GelatinLayerBlock.WATERLOGGED, structureWorldAccess.getBlockState(blockPos2).getValue(GelatinLayerBlock.WATERLOGGED)), 2);
                    ++j;
                }
            }

            return j > 0;
        } else {
            return false;
        }

    }
}
