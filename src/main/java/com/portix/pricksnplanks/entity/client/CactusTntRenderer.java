package com.portix.pricksnplanks.entity.client;

import com.portix.pricksnplanks.entity.client.custom.CactusTntEntityRenderState;
import com.portix.pricksnplanks.entity.custom.CactusTntEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class CactusTntRenderer extends EntityRenderer<CactusTntEntity, CactusTntEntityRenderState> {
    private final BlockRenderManager blockRenderManager;

    public CactusTntRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderManager = context.getBlockRenderManager();
    }

    public void render(CactusTntEntityRenderState cactusTntEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        float f = cactusTntEntityRenderState.fuse;
        if (cactusTntEntityRenderState.fuse < 10.0F) {
            float g = 1.0F - cactusTntEntityRenderState.fuse / 10.0F;
            g = MathHelper.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            matrixStack.scale(h, h, h);
        }

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
        matrixStack.translate(-0.5F, -0.5F, 0.5F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
        if (cactusTntEntityRenderState.blockState != null) {
            TntMinecartEntityRenderer.renderFlashingBlock(
                    this.blockRenderManager, cactusTntEntityRenderState.blockState, matrixStack, vertexConsumerProvider, i, (int)f / 5 % 2 == 0
            );
        }

        matrixStack.pop();
        super.render(cactusTntEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    public CactusTntEntityRenderState createRenderState() {
        return new CactusTntEntityRenderState();
    }

    public void updateRenderState(CactusTntEntity cactusTntEntity, CactusTntEntityRenderState cactusTntEntityRenderState, float f) {
        super.updateRenderState(cactusTntEntity, cactusTntEntityRenderState, f);
        cactusTntEntityRenderState.fuse = cactusTntEntity.getFuse() - f + 1.0F;
        cactusTntEntityRenderState.blockState = cactusTntEntity.getBlockState();
    }
}
