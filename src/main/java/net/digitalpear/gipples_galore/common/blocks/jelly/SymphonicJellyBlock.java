package net.digitalpear.gipples_galore.common.blocks.jelly;

import net.minecraftforge.fml.ModList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SymphonicJellyBlock extends JellyBlock {
    public SymphonicJellyBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            double d = player.getX();
            double e = player.getY();
            double f = player.getZ();

            for(int i = 0; i < 16; ++i) {
                double g = player.getX() + (player.getRandom().nextDouble() - 0.5D) * 16.0D;
                double h = Mth.clamp(player.getY() + (double)(player.getRandom().nextInt(16) - 8), world.getMinBuildHeight(), world.getMinBuildHeight() + ((ServerLevel)world).getLogicalHeight() - 1);
                double j = player.getZ() + (player.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (ModList.get().isLoaded("galosphere")){
                    for (BlockPos pos1 : BlockPos.betweenClosed(pos.offset(-16, -16, -16), pos.offset(16, 16, 16))) {
                        if (world.getBlockState(pos1).is(BuiltInRegistries.BLOCK.get(new ResourceLocation("galosphere", "warped_anchor")))
                                && world.getBlockState(pos1.above()).isAir()
                                && world.getBlockState(pos1).getLightEmission() > 0) {
                            g = pos1.getX() + 0.5f;
                            h = pos1.getY() + 0.5f;
                            j = pos1.getZ() + 0.5f;
                        }
                    }
                }
                if (player.isPassenger()) {
                    player.stopRiding();
                }

                Vec3 vec3d = player.position();
                if (player.randomTeleport(g, h, j, true)) {
                    world.gameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Context.of(player));
                    SoundEvent soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                    world.playSound(null, d, e, f, soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    player.playSound(soundEvent, 1.0F, 1.0F);
                    break;
                }
            }

            player.getCooldowns().addCooldown(Item.byBlock(this), 20);
        }
    }
}
