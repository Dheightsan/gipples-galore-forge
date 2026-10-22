package net.digitalpear.gipples_galore.common.blocks.jelly;

import net.minecraftforge.fml.ModList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.BlockHitResult;

public class WarpJellyBlock extends JellyBlock{
    public WarpJellyBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (eatenState != Blocks.AIR.defaultBlockState()){
            WorldBorder worldBorder = world.getWorldBorder();

            for(int i = 0; i < 1000; ++i) {
                BlockPos blockPos = pos.offset(world.random.nextInt(16) - world.random.nextInt(16), world.random.nextInt(8) - world.random.nextInt(8), world.random.nextInt(16) - world.random.nextInt(16));
                if (ModList.get().isLoaded("galosphere")){
                    for (BlockPos pos1 : BlockPos.betweenClosed(pos.offset(-16, -16, -16), pos.offset(16, 16, 16))) {
                        if (world.getBlockState(pos1).is(BuiltInRegistries.BLOCK.get(new ResourceLocation("galosphere", "warped_anchor")))
                                && world.getBlockState(pos1.above()).isAir()
                                && world.getBlockState(pos1).getLightEmission() > 0) {
                            blockPos = pos1.above();
                        }
                    }
                }
                if (world.getBlockState(blockPos).isAir() && worldBorder.isWithinBounds(blockPos)) {
                    if (world.isClientSide) {
                        for(int j = 0; j < 128; ++j) {
                            double d = world.random.nextDouble();
                            float x = (world.random.nextFloat() - 0.5F) * 0.2F;
                            float y = (world.random.nextFloat() - 0.5F) * 0.2F;
                            float z = (world.random.nextFloat() - 0.5F) * 0.2F;
                            double e = Mth.lerp(d, blockPos.getX(), pos.getX()) + (world.random.nextDouble() - 0.5) + 0.5;
                            double k = Mth.lerp(d, blockPos.getY(), pos.getY()) + world.random.nextDouble() - 0.5;
                            double l = Mth.lerp(d, blockPos.getZ(), pos.getZ()) + (world.random.nextDouble() - 0.5) + 0.5;
                            world.addParticle(ParticleTypes.PORTAL, e, k, l, x, y, z);
                        }
                    } else {
                        world.setBlock(blockPos, eatenState, 2);
                        world.removeBlock(pos, false);
                    }

                    return;
                }
            }

        }
    }
}
