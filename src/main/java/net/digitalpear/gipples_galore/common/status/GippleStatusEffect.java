package net.digitalpear.gipples_galore.common.status;

import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntity;
import net.digitalpear.gipples_galore.init.GGDamageTypes;
import net.digitalpear.gipples_galore.init.GGEntities;
import net.digitalpear.gipples_galore.init.GGSoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class GippleStatusEffect extends MobEffect {
    public GippleStatusEffect() {
        super(MobEffectCategory.HARMFUL, 0xc6d1f2);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int duration = entity.getEffect(this).getDuration();
        if(entity.getRandom().nextInt((duration/4)+1) < 6 || entity.getRandom().nextInt(10) == 1) {
            entity.playSound(GGSoundEvents.ENTITY_GIPPLE_AMBIENT, 1 / ((duration+1)/60F), 0.9F + entity.getRandom().nextFloat() / 5F);
        }
        if(duration <= 1) {
            entity.playSound(GGSoundEvents.ENTITY_GIPPLE_HURT, 1, 1);
            Level world = entity.level();
            if (world != null) {
                if(!world.isClientSide()) {
                    GippleEntity gipple = GGEntities.GIPPLE.create(world);
                    if(gipple != null) {
                        gipple.setPos(entity.position());
                        gipple.setCustomName(entity.getName());
                        gipple.setXRot(entity.getXRot());
                        gipple.setYRot(entity.getYRot());
                        gipple.setYHeadRot(entity.getYHeadRot());
                        gipple.setPersistenceRequired();
                        world.addFreshEntity(gipple);
                    }
                }
                entity.hurt(GGDamageTypes.gippleEffect(world), entity.getMaxHealth());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 10 == 0 || duration <= 1;
    }
}
