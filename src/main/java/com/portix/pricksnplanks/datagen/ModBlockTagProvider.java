package com.portix.pricksnplanks.datagen;

import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Blocks.CACTUSES)
                .add(Blocks.CACTUS)
                .add(ModBlocks.SMOOTH_CACTUS)
                .add(ModBlocks.SKINNED_CACTUS);

        valueLookupBuilder(ModTags.Blocks.CACTUS_TNT)
                .add(ModBlocks.CACTUS_TNT);

        valueLookupBuilder(BlockTags.PLANKS).add(ModBlocks.CACTUS_PLANKS);
        valueLookupBuilder(BlockTags.PRESSURE_PLATES).add(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE);
        valueLookupBuilder(BlockTags.BUTTONS).add(ModBlocks.CACTUS_PLANK_BUTTON);
        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.CACTUS_PLANK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CACTUS_PLANK_FENCE_GATE);

        valueLookupBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.CACTUS_PLANKS)
                .add(ModBlocks.CACTUS_PLANK_SLAB)
                .add(ModBlocks.CACTUS_PLANK_STAIRS)
                .add(ModBlocks.CACTUS_PLANK_FENCE)
                .add(ModBlocks.CACTUS_PLANK_FENCE_GATE)
                .add(ModBlocks.CACTUS_PLANK_DOOR)
                .add(ModBlocks.CACTUS_PLANK_TRAPDOOR)
                .add(ModBlocks.CACTUS_PLANK_BUTTON)
                .add(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE)
                .add(ModBlocks.CACTUS_PLANK_SIGN)
                .add(ModBlocks.CACTUS_PLANK_WALL_SIGN)
                .add(ModBlocks.CACTUS_PLANK_HANGING_SIGN)
                .add(ModBlocks.CACTUS_PLANK_WALL_HANGING_SIGN);
    }
}
