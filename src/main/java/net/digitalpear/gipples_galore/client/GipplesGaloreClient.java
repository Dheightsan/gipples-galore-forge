package net.digitalpear.gipples_galore.client;

import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntityRenderer;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntityRenderer;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

// Port a Forge: eventos del cliente en lugar de ClientModInitializer. Solo se carga desde el
// cliente (ver GipplesGalore).
public class GipplesGaloreClient {

    public static void init(IEventBus modBus) {
        modBus.addListener(GipplesGaloreClient::onRenderers);
        modBus.addListener(GipplesGaloreClient::onClientSetup);
    }

    private static void onRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GGEntities.GIPPLE, GippleEntityRenderer::new);
        event.registerEntityRenderer(GGEntities.ANEUPLOIDIAN, AneuploidianEntityRenderer::new);
    }

    @SuppressWarnings("removal")
    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(GGBlocks.GELATIN_LAYER, RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(GGBlocks.GIPPLEPAD, RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(GGBlocks.GELATINOUS_GROWTH, RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(GGBlocks.POTTED_GELATINOUS_GROWTH, RenderType.cutout());
        });
    }
}
