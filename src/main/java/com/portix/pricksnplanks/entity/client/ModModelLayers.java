package com.portix.pricksnplanks.entity.client;

import com.portix.pricksnplanks.PricksnPlanks;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer THROWABLE_CACTUS = new EntityModelLayer(Identifier.of(PricksnPlanks.MOD_ID, "throwable_cactus"), "main");

    public static final EntityModelLayer CACTUS_BOAT = new EntityModelLayer(
            Identifier.of(PricksnPlanks.MOD_ID, "boat/cactus_boat_entity"), "main"
    );

    public static final EntityModelLayer CACTUS_CHEST_BOAT = new EntityModelLayer(
            Identifier.of(PricksnPlanks.MOD_ID, "boat/cactus_chest_boat_entity"), "main"
    );

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(THROWABLE_CACTUS, ThrowableCactusEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(CACTUS_BOAT, BoatEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CACTUS_CHEST_BOAT, BoatEntityModel::getChestTexturedModelData);
    }
}
