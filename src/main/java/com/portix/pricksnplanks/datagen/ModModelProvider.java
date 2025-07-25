package com.portix.pricksnplanks.datagen;

import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.item.ModItems;
import com.portix.pricksnplanks.util.ModTags;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.SMOOTH_CACTUS);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SKINNED_CACTUS);
        blockStateModelGenerator.registerSimpleState(ModBlocks.CACTUS_TNT);

        BlockStateModelGenerator.BlockTexturePool cactusPlanksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CACTUS_PLANKS);
        cactusPlanksPool.stairs(ModBlocks.CACTUS_PLANK_STAIRS);
        cactusPlanksPool.slab(ModBlocks.CACTUS_PLANK_SLAB);
        cactusPlanksPool.button(ModBlocks.CACTUS_PLANK_BUTTON);
        cactusPlanksPool.pressurePlate(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE);
        cactusPlanksPool.fence(ModBlocks.CACTUS_PLANK_FENCE);
        cactusPlanksPool.fenceGate(ModBlocks.CACTUS_PLANK_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.CACTUS_PLANK_DOOR);
        blockStateModelGenerator.registerOrientableTrapdoor(ModBlocks.CACTUS_PLANK_TRAPDOOR);

        blockStateModelGenerator.registerHangingSign(ModBlocks.CACTUS_PLANKS, ModBlocks.CACTUS_PLANK_HANGING_SIGN, ModBlocks.CACTUS_PLANK_WALL_HANGING_SIGN);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerSubModel(ModItems.THROWABLE_CACTUS, "_2d", Models.GENERATED);
        itemModelGenerator.register(ModItems.CACTUS_SPIKES, Models.GENERATED);

        itemModelGenerator.register(ModItems.CACTUS_STEW, Models.GENERATED);

        itemModelGenerator.register(ModItems.CACTUS_PLANK_SIGN_ITEM, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CACTUS_PLANK_HANGING_SIGN_ITEM, Models.GENERATED);

        itemModelGenerator.register(ModItems.CACTUS_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CACTUS_CHEST_BOAT, Models.GENERATED);

        itemModelGenerator.register(ModItems.CACTUS_POTTERY_SHERD, Models.GENERATED);

        itemModelGenerator.register(ModItems.THORNS_MUSIC_DISC, Models.GENERATED);
    }
}
