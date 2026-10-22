package net.digitalpear.gipples_galore.init.data;

import net.digitalpear.gipples_galore.init.GGSoundEvents;
import net.minecraft.world.level.block.SoundType;

public class GGBlockSoundGroups {

    public static final SoundType AMOEBALITH = new SoundType(1.0F, 1.0F,
            GGSoundEvents.BLOCK_AMOEBALITH_BREAK,
            GGSoundEvents.BLOCK_AMOEBALITH_STEP,
            GGSoundEvents.BLOCK_AMOEBALITH_PLACE,
            GGSoundEvents.BLOCK_AMOEBALITH_HIT,
            GGSoundEvents.BLOCK_AMOEBALITH_FALL);

    public static final SoundType GELATITE = new SoundType(1.0F, 1.0F,
            GGSoundEvents.BLOCK_GELATITE_BREAK,
            GGSoundEvents.BLOCK_GELATITE_STEP,
            GGSoundEvents.BLOCK_GELATITE_PLACE,
            GGSoundEvents.BLOCK_GELATITE_HIT,
            GGSoundEvents.BLOCK_GELATITE_FALL);
}
