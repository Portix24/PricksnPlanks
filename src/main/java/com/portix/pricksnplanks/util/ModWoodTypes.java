package com.portix.pricksnplanks.util;

import com.portix.pricksnplanks.PricksnPlanks;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModWoodTypes {
    /*
    public static final WoodType CACTUS = new WoodType(
            Identifier.of(PricksnPlanks.MOD_ID, "cactus").toString(),
            ModBlockSetTypes.CACTUS,
            BlockSoundGroup.BAMBOO_WOOD,
            BlockSoundGroup.BAMBOO_WOOD_HANGING_SIGN,
            SoundEvents.BLOCK_BAMBOO_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.BLOCK_BAMBOO_WOOD_FENCE_GATE_OPEN
    );
     */

    public static final WoodType CACTUS = new WoodTypeBuilder()
            .fenceGateCloseSound(SoundEvents.BLOCK_BAMBOO_WOOD_FENCE_GATE_CLOSE)
            .fenceGateOpenSound(SoundEvents.BLOCK_BAMBOO_WOOD_FENCE_GATE_OPEN)
            .hangingSignSoundGroup(BlockSoundGroup.BAMBOO_WOOD_HANGING_SIGN)
            .soundGroup(BlockSoundGroup.BAMBOO_WOOD)
            .register(
            Identifier.of(PricksnPlanks.MOD_ID, "cactus"),
            ModBlockSetTypes.CACTUS);


    public static void registerModWoodTypes() {
        PricksnPlanks.LOGGER.info("Registering Mod Wood Types for" + PricksnPlanks.MOD_ID);
    }
}
