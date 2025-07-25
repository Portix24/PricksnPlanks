package com.portix.pricksnplanks.entity.client;

import com.nimbusds.oauth2.sdk.pkce.CodeChallenge;
import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.entity.client.custom.ThrowableCactusRenderState;
import com.portix.pricksnplanks.entity.custom.ThrowableCactusEntity;
import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class ThrowableCactusEntityRenderer extends EntityRenderer<ThrowableCactusEntity, ThrowableCactusRenderState> {
    protected ThrowableCactusEntityModel model;

    public ThrowableCactusEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new ThrowableCactusEntityModel(context.getPart(ModModelLayers.THROWABLE_CACTUS));
    }

    /*
    @Override
    public void render(ThrowableCactusEntity entity, float entityYaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
     */
    @Override
    public void render(ThrowableCactusRenderState entityRenderState, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        // Move to the entity position
        matrices.translate(0.0, 0.0, 0.0);

        double ageSpinProgress = entityRenderState.entity.getSpinAgeCounter();
        // Simple consistent spin using age
        // there's supposed to be tickDelta added to the age, but I cannot access it here
        float spinYaw = (float) (ageSpinProgress) * 10f;   // Y-axis (horizontal spin)
        float spinPitch = (float) (ageSpinProgress) * 5f;  // Z-axis (tumbling)

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(spinYaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(spinPitch));

        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(PricksnPlanks.MOD_ID, "textures/item/throwable_cactus_3d.png")), false, false);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entityRenderState, matrices, vertexConsumers, light);
    }

    @Override
    public void updateRenderState(ThrowableCactusEntity entity, ThrowableCactusRenderState state, float tickDelta) {
        state.entity = entity;
        state.stack = entity.getWeaponStack();
        state.tickDelta = tickDelta;
        if (entity.shouldSpin()) {
            state.entity.increaseSpinAgeCounter();
        }
        super.updateRenderState(entity, state, tickDelta);
    }

    @Override
    public ThrowableCactusRenderState createRenderState() {
        return new ThrowableCactusRenderState();
    }
}
