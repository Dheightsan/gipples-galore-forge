package net.digitalpear.gipples_galore.common.blocks.jelly;

import net.digitalpear.gipples_galore.init.GGGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JellyBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty HALVED = BooleanProperty.create("halved");
    protected static final VoxelShape FULL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_SHAPE = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape DOWN_SHAPE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    /*
        -Much of this block's code has been taken from slime blocks, I will comment on all custom methods describing what they do
     */
    public JellyBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALVED, false).setValue(FACING, Direction.NORTH));
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALVED);
        builder.add(FACING);
    }



    /*
        -Special effect for eating the jelly (is set in other classes.)
     */
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){

    }
    /*
        -If the player is able to eat, eat half the block (Changes outline shape and piston behavior).
        -If half the block has already been eaten, then remove the block.
    */
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getFoodData().needsFood() || player.isCreative()) {

            BlockState finalState;
            //Add food levels
            player.getFoodData().eat(2, 0.8f);

            world.playSound(player, pos, SoundEvents.HONEY_DRINK, SoundSource.BLOCKS, 1.0f, 1.0f);
            player.swing(hand);

            if (state.getValue(HALVED)) {
                finalState = Blocks.AIR.defaultBlockState();
            }
            else{
                finalState = this.defaultBlockState().setValue(HALVED, true).setValue(FACING,hit.getDirection());
            }
            world.setBlock(pos, finalState, 2);

            if (world.getGameRules().getBoolean(GGGameRules.SHOULD_APPLY_JELLY_EFFECTS)) {
                applySpecialEffects(state, finalState, world, pos, player, hand, hit);
            }

            return InteractionResult.SUCCESS;
        }
        else {
            return InteractionResult.PASS;
        }
    }


    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(HALVED) ? (switch (state.getValue(FACING)){
            default -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            case UP -> UP_SHAPE;
            case DOWN -> DOWN_SHAPE;
        }) : FULL_SHAPE;
    }

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(world, state, pos, entity, fallDistance);
        } else {
            entity.causeFallDamage(fallDistance, 0.0F, entity.damageSources().fall());
        }
    }



    /*
        Methods brought in from the slime block code
     */
    public void updateEntityAfterFallOn(BlockGetter world, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(world, entity);
        } else {
            this.bounce(entity);
        }
    }
    private void bounce(Entity entity) {
        Vec3 vec3d = entity.getDeltaMovement();
        if (vec3d.y < 0.0D) {
            double d = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setDeltaMovement(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getDeltaMovement().y);
        if (d < 0.1D && !entity.isSteppingCarefully()) {
            double e = 0.4D + d * 0.2D;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(e, 1.0D, e));
        }

        super.stepOn(world, pos, state, entity);
    }
}
