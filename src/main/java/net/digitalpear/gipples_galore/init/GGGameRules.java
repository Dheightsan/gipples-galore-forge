package net.digitalpear.gipples_galore.init;


import net.minecraft.world.level.GameRules;

public class GGGameRules {

    // Port a Forge: GameRules.register directo, sin GameRuleRegistry de Fabric.
    public static final GameRules.Key<GameRules.BooleanValue> SHOULD_APPLY_JELLY_EFFECTS =
            GameRules.register("doJellyEffects", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static void init(){}

}
