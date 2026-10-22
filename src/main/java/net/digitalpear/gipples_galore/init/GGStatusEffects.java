package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.status.GippleStatusEffect;
import net.digitalpear.gipples_galore.GGRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class GGStatusEffects {
    public static final MobEffect GIPPLE = new GippleStatusEffect();

    public static void init() {
        GGRegistry.register(Registries.MOB_EFFECT, new ResourceLocation(GipplesGalore.MOD_ID, "gipple"), GIPPLE);
    }
}
