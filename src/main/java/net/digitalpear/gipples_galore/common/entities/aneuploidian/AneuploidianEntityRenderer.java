package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AneuploidianEntityRenderer extends GeoEntityRenderer<AneuploidianEntity> {
    public AneuploidianEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AneuploidianEntityModel());
    }

}