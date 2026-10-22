package net.digitalpear.gipples_galore;

import net.digitalpear.gipples_galore.client.GipplesGaloreClient;
import net.digitalpear.gipples_galore.common.features.GGFeatures;
import net.digitalpear.gipples_galore.init.*;
import net.digitalpear.gipples_galore.init.data.GGData;
import net.digitalpear.gipples_galore.init.features.GGPlacedFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Port a Forge: @Mod en lugar de ModInitializer. Los init() del original se llaman en el mismo
// orden, pero dentro del primer RegisterEvent (ver GGRegistry).
@Mod(GipplesGalore.MOD_ID)
public class GipplesGalore {

    public static final String MOD_ID = "gipples_galore";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public GipplesGalore() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        GGRegistry.setBootstrap(() -> {
            GGItems.init();
            GGBlocks.init();
            GGEntities.init();
            GGSoundEvents.init();
            GGFeatures.init();
            GGPlacedFeatures.init();
            GGBannerPatterns.init();
            GGData.init();
            GGStatusEffects.init();
            GGPaintings.init();
        });
        GGGameRules.init();

        modBus.addListener(GGRegistry::onRegister);
        modBus.addListener(GGEntities::onAttributes);
        modBus.addListener(GGItems::onCreativeTabs);
        modBus.addListener(this::onCommonSetup);
        MinecraftForge.EVENT_BUS.addListener(GGData::onLootTableLoad);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            GipplesGaloreClient.init(modBus);
        }

        LOGGER.info("False Futures has finished registering successfully.");
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        // En Forge la maceta vacia no conoce las plantas de otros mods hasta que se anotan aqui.
        event.enqueueWork(() -> ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(GGRegistry.idOf(GGBlocks.GELATINOUS_GROWTH), () -> GGBlocks.POTTED_GELATINOUS_GROWTH));
    }
}
