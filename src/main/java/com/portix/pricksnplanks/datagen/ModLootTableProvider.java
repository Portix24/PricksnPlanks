package com.portix.pricksnplanks.datagen;

import com.portix.pricksnplanks.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.impl.client.particle.ParticleFactoryRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SMOOTH_CACTUS);
        addDrop(ModBlocks.SKINNED_CACTUS);
        addDrop(ModBlocks.CACTUS_TNT);

        addDrop(ModBlocks.CACTUS_PLANKS);

        addDrop(ModBlocks.CACTUS_PLANK_STAIRS);
        addDrop(ModBlocks.CACTUS_PLANK_SLAB, slabDrops(ModBlocks.CACTUS_PLANK_SLAB));
        addDrop(ModBlocks.CACTUS_PLANK_FENCE);
        addDrop(ModBlocks.CACTUS_PLANK_FENCE_GATE);
        addDrop(ModBlocks.CACTUS_PLANK_DOOR, doorDrops(ModBlocks.CACTUS_PLANK_DOOR));
        addDrop(ModBlocks.CACTUS_PLANK_TRAPDOOR);
        addDrop(ModBlocks.CACTUS_PLANK_BUTTON);
        addDrop(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE);

        /*
        addDrop(ModBlocks.CACTUS_PLANK_SIGN);
        addDrop(ModBlocks.CACTUS_PLANK_WALL_SIGN);

        addDrop(ModBlocks.CACTUS_PLANK_HANGING_SIGN);
        addDrop(ModBlocks.CACTUS_PLANK_WALL_HANGING_SIGN);
         */
    }

    private void tryAddDrop(Block block, LootTable.Builder builder) {
        if (block.asItem() == Items.AIR) {
            System.out.println("Skipping loot table for block with no item: " + Registries.BLOCK.getId(block));
        } else {
            addDrop(block, builder);
        }
    }

    private void tryAddDrop(Block block) {
        if (block.asItem() == Items.AIR) {
            System.out.println("Skipping loot table for block with no item: " + Registries.BLOCK.getId(block));
        } else {
            addDrop(block);
        }
    }
}
