package net.digitalpear.gipples_galore.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.HibernatingGippleBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class GippleColonyFeature extends VegetationPatchFeature {
    public GippleColonyFeature(Codec<VegetationPatchConfiguration> codec) {
        super(codec);
    }

    protected Set<BlockPos> placeGroundPatch(WorldGenLevel world, VegetationPatchConfiguration config, RandomSource random, BlockPos pos, Predicate<BlockState> replaceable, int radiusX, int radiusZ) {
        Set<BlockPos> set = super.placeGroundPatch(world, config, random, pos, replaceable, radiusX, radiusZ);
        Set<BlockPos> set2 = new HashSet();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        Iterator var11 = set.iterator();

        BlockPos blockPos;
        while(var11.hasNext()) {
            blockPos = (BlockPos)var11.next();
            if (!isSolidBlockAroundPos(world, set, blockPos, mutable)) {
//                generateLichen(world, random, pos);
                set2.add(blockPos);
            }
        }

        var11 = set2.iterator();

        while(var11.hasNext()) {
            blockPos = (BlockPos)var11.next();
            world.setBlock(blockPos, Blocks.WATER.defaultBlockState(), 2);

        }


        return set2;
    }
    private static boolean isSolidBlockAroundPos(WorldGenLevel world, Set<BlockPos> positions, BlockPos pos, BlockPos.MutableBlockPos mutablePos) {
        return isSolidBlockSide(world, pos, mutablePos, Direction.NORTH) || isSolidBlockSide(world, pos, mutablePos, Direction.EAST) || isSolidBlockSide(world, pos, mutablePos, Direction.SOUTH) || isSolidBlockSide(world, pos, mutablePos, Direction.WEST) || isSolidBlockSide(world, pos, mutablePos, Direction.DOWN);
    }

    private static boolean isSolidBlockSide(WorldGenLevel world, BlockPos pos, BlockPos.MutableBlockPos mutablePos, Direction direction) {
        mutablePos.setWithOffset(pos, direction);
        return !world.getBlockState(mutablePos).isFaceSturdy(world, mutablePos, direction.getOpposite());
    }

    protected boolean placeVegetation(WorldGenLevel world, VegetationPatchConfiguration config, ChunkGenerator generator, RandomSource random, BlockPos pos) {
        if (super.placeVegetation(world, config, generator, random, pos.below())) {
            BlockState blockState = world.getBlockState(pos);
            if (blockState.hasProperty(BlockStateProperties.WATERLOGGED) && !(Boolean)blockState.getValue(BlockStateProperties.WATERLOGGED)) {
                world.setBlock(pos, blockState.setValue(BlockStateProperties.WATERLOGGED, true), 2);
            }
            if (random.nextInt(7) == 0){
                generatePillar(world, random, pos);
            }


            return true;
        } else {
            return false;
        }
    }

    public void generatePillar(WorldGenLevel world, RandomSource random, BlockPos pos){
        int initialHeight = random.nextIntBetweenInclusive(2, 3);
        for (int i = -2; i < initialHeight; i++){
            for (int x = 2; x < 6; x++){
                if (!world.getBlockState(pos.above(i)).canOcclude()){
                    placeBlock(world, random, pos.above(i).relative(Direction.from3DDataValue(x)));
                    if (random.nextBoolean()){
                        world.setBlock(pos.above(i + 1).relative(Direction.from3DDataValue(x)), GGBlocks.GELATINOUS_GROWTH.defaultBlockState(), 2);
                    }

                }
            }
            if (!world.getBlockState(pos.above(i)).canOcclude()){
                placeBlock(world, random, pos.above(i));
                if (random.nextBoolean()){
                    world.setBlock(pos.above(i + 1), GGBlocks.GELATINOUS_GROWTH.defaultBlockState(), 2);
                }
            }
        }
        int finalHeight = initialHeight + random.nextIntBetweenInclusive(2, 4);
        for (int i = initialHeight; i < finalHeight; i++) {
            if (!world.getBlockState(pos.above(i)).canOcclude()) {
                placeBlock(world, random, pos.above(i));
            }
        }
        if (!world.getBlockState(pos.above(finalHeight)).isAir()) {
            world.setBlock(pos.above(finalHeight), GGBlocks.GELATINOUS_GROWTH.defaultBlockState(), 2);
        }

    }
    public void placeBlock(WorldGenLevel world, RandomSource random, BlockPos pos){
        world.setBlock(pos, random.nextInt(10) < 8 ? GGBlocks.GELATIN_BLOCK.defaultBlockState() : GGBlocks.HIBERNATING_GIPPLE.defaultBlockState().setValue(HibernatingGippleBlock.FACING, Direction.from3DDataValue(random.nextIntBetweenInclusive(2, 5))), 2);
    }


    protected boolean placeGround(WorldGenLevel world, VegetationPatchConfiguration config, Predicate<BlockState> replaceable, RandomSource random, BlockPos.MutableBlockPos pos, int depth) {
        for(int i = 0; i < depth; ++i) {
            BlockState gelatite = config.groundState.getState(random, pos);
            BlockState floor = world.getBlockState(pos);
            if (!gelatite.is(floor.getBlock())) {
                if (!replaceable.test(floor)) {
                    return i != 0;
                }

                Block stone = world.getBlockState(pos).is(BlockTags.DEEPSLATE_ORE_REPLACEABLES) ? GGBlocks.AMOEBALITH : GGBlocks.GELATITE;
                world.setBlock(pos, stone.defaultBlockState(), 2);


                pos.move(config.surface.getDirection());
            }
        }

        return true;
    }
}

