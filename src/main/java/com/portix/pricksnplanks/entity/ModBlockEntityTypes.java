package com.portix.pricksnplanks.entity;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.entity.custom.CactusHangingSignBlockEntity;
import com.portix.pricksnplanks.entity.custom.CactusSignBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static final BlockEntityType<CactusSignBlockEntity> CACTUS_SIGN_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(PricksnPlanks.MOD_ID, "cactus_plank_sign"),
            FabricBlockEntityTypeBuilder.create(CactusSignBlockEntity::new, ModBlocks.CACTUS_PLANK_SIGN, ModBlocks.CACTUS_PLANK_WALL_SIGN).build()
    );

    public static final BlockEntityType<CactusHangingSignBlockEntity> CACTUS_HANGING_SIGN_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(PricksnPlanks.MOD_ID, "cactus_plank_hanging_sign"),
            FabricBlockEntityTypeBuilder.create(CactusHangingSignBlockEntity::new, ModBlocks.CACTUS_PLANK_HANGING_SIGN, ModBlocks.CACTUS_PLANK_WALL_HANGING_SIGN).build()
    );

    public static void registerModBlockEntities() {
        PricksnPlanks.LOGGER.info("Registering Mod BlockEntities for " + PricksnPlanks.MOD_ID);
    }
}
