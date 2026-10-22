package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AneuploidianEntityModel extends DefaultedEntityGeoModel<AneuploidianEntity> {

    public AneuploidianEntityModel() {
        super(new ResourceLocation(GipplesGalore.MOD_ID, "aneuploidian"));
    }

    @Override
    public ResourceLocation getAnimationResource(AneuploidianEntity entity) {
        return new ResourceLocation(GipplesGalore.MOD_ID, "animations/aneuploidian.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(AneuploidianEntity entity) {
        return new ResourceLocation(GipplesGalore.MOD_ID, "geo/aneuploidian.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AneuploidianEntity entity) {
        return new ResourceLocation(GipplesGalore.MOD_ID, "textures/entity/aneuploidian.png");
    }

}