package net.digitalpear.gipples_galore.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class GGFoodComponents {
    public static final FoodProperties GELATIN = new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).fast().build();
    public static final FoodProperties GAPPLE = new FoodProperties.Builder().nutrition(5).saturationMod(0.375F).effect(new MobEffectInstance(GGStatusEffects.GIPPLE,1200,0),1).alwaysEat().build();
}
