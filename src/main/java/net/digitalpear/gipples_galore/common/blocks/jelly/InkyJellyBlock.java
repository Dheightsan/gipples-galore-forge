package net.digitalpear.gipples_galore.common.blocks.jelly;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class InkyJellyBlock extends JellyBlock{
    public InkyJellyBlock(Properties settings) {
        super(settings);
    }

    @Override
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1));
    }
}
