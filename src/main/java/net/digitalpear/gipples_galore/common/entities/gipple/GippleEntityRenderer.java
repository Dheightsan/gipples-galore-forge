package net.digitalpear.gipples_galore.common.entities.gipple;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GippleEntityRenderer<T extends GippleEntity> extends GeoEntityRenderer<T> {

    public GippleEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GippleEntityModel<>());
    }

    @Override
    public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);


        if (animatable.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
            this.shadowRadius *= 0.6F;

        } else if (animatable.isLuminous()) {
            poseStack.scale(1.3F, 1.3F, 1.3F);
            this.shadowRadius *= 1.3F;
        }
    }

    @Override
    public void actuallyRender(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        if (animatable.isLuminous()) {
            packedLight = 255;
            packedOverlay = OverlayTexture.NO_OVERLAY;
            red = 1;
            green = 1;
            blue = 1;
            alpha = 1;
        }
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }
}