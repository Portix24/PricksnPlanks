package com.portix.pricksnplanks.util;

import com.portix.pricksnplanks.PricksnPlanks;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlockSetTypes {
    //public static final BlockSetType CACTUS = new BlockSetType(Identifier.of(PricksnPlanks.MOD_ID, "cactus").toString());
    public static final BlockSetType CACTUS = new BlockSetTypeBuilder()
            .soundGroup(BlockSoundGroup.BAMBOO_WOOD)
            .register(Identifier.of(PricksnPlanks.MOD_ID, "cactus"));

    public static void registerModBlockSetTypes() {
        PricksnPlanks.LOGGER.info("Registering Mod Block Sets Types for" + PricksnPlanks.MOD_ID);
    }
}
