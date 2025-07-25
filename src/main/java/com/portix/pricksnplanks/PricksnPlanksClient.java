package com.portix.pricksnplanks;

import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.entity.ModBlockEntityTypes;
import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.entity.client.CactusTntRenderer;
import com.portix.pricksnplanks.entity.client.ModModelLayers;
import com.portix.pricksnplanks.entity.client.ThrowableCactusEntityModel;
import com.portix.pricksnplanks.entity.client.ThrowableCactusEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.BoatEntityRenderer;

public class PricksnPlanksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelLayers.registerModelLayers();

        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, ModBlocks.SMOOTH_CACTUS, ModBlocks.SKINNED_CACTUS, ModBlocks.CACTUS_PLANK_DOOR, ModBlocks.CACTUS_PLANK_TRAPDOOR);

        BlockEntityRendererFactories.register(ModBlockEntityTypes.CACTUS_SIGN_BLOCK_ENTITY, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.CACTUS_HANGING_SIGN_BLOCK_ENTITY, HangingSignBlockEntityRenderer::new);

        EntityRendererRegistry.register(
                ModEntityTypes.THROWABLE_CACTUS_ENTITY,
                ThrowableCactusEntityRenderer::new
        );

        EntityRendererRegistry.register(
                ModEntityTypes.CACTUS_BOAT,
                context -> new BoatEntityRenderer(context, ModModelLayers.CACTUS_BOAT)
        );

        EntityRendererRegistry.register(
                ModEntityTypes.CACTUS_CHEST_BOAT,
                context -> new BoatEntityRenderer(context, ModModelLayers.CACTUS_CHEST_BOAT)
        );

        EntityRendererRegistry.register(ModEntityTypes.CACTUS_TNT, CactusTntRenderer::new);
    }
}
