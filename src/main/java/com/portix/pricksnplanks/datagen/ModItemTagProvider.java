package com.portix.pricksnplanks.datagen;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.item.ModItems;
import com.portix.pricksnplanks.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.CACTUS_PLANK_CRAFTABLES)
                .add(Blocks.CACTUS.asItem())
                .add(ModBlocks.SMOOTH_CACTUS.asItem())
                .add(ModBlocks.SKINNED_CACTUS.asItem());

        valueLookupBuilder(ModTags.Items.DRY_GRASS)
                .add(Blocks.SHORT_DRY_GRASS.asItem())
                .add(Blocks.TALL_DRY_GRASS.asItem());

        valueLookupBuilder(ModTags.Items.CACTUS_STEW_INGREDIENTS)
                .add(Blocks.CACTUS.asItem());
                //.add(ModBlocks.SMOOTH_CACTUS.asItem());

        valueLookupBuilder(ItemTags.BOATS)
                .add(ModItems.CACTUS_BOAT);

        valueLookupBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.CACTUS_CHEST_BOAT);

        //PricksnPlanks.LOGGER.info("BlockItem for cactus planks: " + ModBlocks.CACTUS_PLANKS.asItem());
        valueLookupBuilder(ItemTags.PLANKS).add(ModBlocks.CACTUS_PLANKS.asItem());
        //copy(BlockTags.PLANKS, ItemTags.PLANKS);

        valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE.asItem());
        valueLookupBuilder(ItemTags.BUTTONS).add(ModBlocks.CACTUS_PLANK_BUTTON.asItem());
        valueLookupBuilder(ItemTags.FENCES).add(ModBlocks.CACTUS_PLANK_FENCE.asItem());
        valueLookupBuilder(ItemTags.FENCE_GATES).add(ModBlocks.CACTUS_PLANK_FENCE_GATE.asItem());

        valueLookupBuilder(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.CACTUS_POTTERY_SHERD);
    }
}
