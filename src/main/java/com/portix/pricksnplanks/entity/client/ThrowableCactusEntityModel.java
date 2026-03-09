package com.portix.pricksnplanks.entity.client;

import com.portix.pricksnplanks.entity.client.custom.ThrowableCactusRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

public class ThrowableCactusEntityModel extends EntityModel<ThrowableCactusRenderState> {
    private final ModelPart throwableCactus;

    public ThrowableCactusEntityModel(ModelPart root) {
        super(root);
        this.throwableCactus = root.getChild("throwableCactus");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData bone = modelPartData.addChild("throwableCactus", ModelPartBuilder.create().uv(0, 10).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 5).cuboid(-2.5F, -2.5F, 0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0f, 0f, 0f)); //ModelTransform.origin(8.0F, 24.0F, -8.0F)

        return TexturedModelData.of(modelData, 16, 16);
    }

    /*
    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        throwableCactus.render(matrices, vertexConsumer, light, overlay, color);
    }
     */
}
