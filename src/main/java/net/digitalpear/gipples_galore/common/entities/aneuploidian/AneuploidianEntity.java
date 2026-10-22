package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AneuploidianEntity extends Monster implements Enemy, FlyingAnimal, GeoEntity {
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("aneuploidian.attack");
    protected static final RawAnimation AMBIENT_ANIM = RawAnimation.begin().thenLoop("aneuploidian.ambient");
    private boolean isAttacking = false;


    public AneuploidianEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.lookControl = new LookControl(this);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 20;
    }

    @Override
    public void tick() {
        isAttacking = this.getLastHurtMob() != null && getLastHurtMobTimestamp() < this.tickCount + 80;
        super.tick();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 20.0D));
        this.goalSelector.addGoal(1, new AneuploidianAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new AneuploidianTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new AneuploidianTargetGoal<>(this, PathfinderMob.class, false));
    }

    public static AttributeSupplier.Builder createSomethingAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 36.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.FLYING_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4)
                .add(Attributes.MOVEMENT_SPEED, 0)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.8F;
    }


    /*
        Flying code.
     */
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world);
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanFloat(true);
        birdNavigation.setCanPassDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    /*
            Geckolib
         */

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "Walking", 3, this::setAnimations));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setAnimations(final AnimationState<E> event) {
        if (isAttacking) {
            return event.setAndContinue(ATTACK_ANIM);
        } else {
            return event.setAndContinue(AMBIENT_ANIM);
        }
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }

    static class AneuploidianAttackGoal extends MeleeAttackGoal {
        private final AneuploidianEntity gipple;
        private int ticks;

        public AneuploidianAttackGoal(AneuploidianEntity gipple, double speed, boolean pauseWhenMobIdle) {
            super(gipple, speed, pauseWhenMobIdle);
            this.gipple = gipple;
        }

        public void start() {
            super.start();
            this.ticks = 0;
        }

        public void stop() {
            super.stop();
            this.gipple.setAggressive(false);
        }

        public void tick() {
            ++this.ticks;
            this.gipple.setAggressive(this.ticks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
            super.tick();
        }
    }
}