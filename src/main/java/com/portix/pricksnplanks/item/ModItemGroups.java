package com.portix.pricksnplanks.item;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CACTUS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PricksnPlanks.MOD_ID, "cactus_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(Blocks.CACTUS))
                    .displayName(Text.translatable("itemgroup.pricksnplanks.cactus_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(Blocks.CACTUS);
                        entries.add(ModBlocks.SMOOTH_CACTUS);
                        entries.add(ModBlocks.SKINNED_CACTUS);

                        entries.add(ModItems.THROWABLE_CACTUS);
                        entries.add(ModItems.CACTUS_SPIKES);

                        entries.add(ModBlocks.CACTUS_PLANKS);
                        entries.add(ModBlocks.CACTUS_PLANK_SLAB);
                        entries.add(ModBlocks.CACTUS_PLANK_STAIRS);
                        entries.add(ModBlocks.CACTUS_PLANK_FENCE);
                        entries.add(ModBlocks.CACTUS_PLANK_FENCE_GATE);
                        entries.add(ModBlocks.CACTUS_PLANK_BUTTON);
                        entries.add(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE);
                        entries.add(ModBlocks.CACTUS_PLANK_DOOR);
                        entries.add(ModBlocks.CACTUS_PLANK_TRAPDOOR);

                        entries.add(ModItems.CACTUS_PLANK_SIGN_ITEM);
                        entries.add(ModItems.CACTUS_PLANK_HANGING_SIGN_ITEM);

                        entries.add(ModItems.CACTUS_BOAT);
                        entries.add(ModItems.CACTUS_CHEST_BOAT);

                        entries.add(ModItems.CACTUS_POTTERY_SHERD);

                        entries.add(ModItems.CACTUS_STEW);

                        entries.add(ModItems.THORNS_MUSIC_DISC);
                        entries.add(ModBlocks.CACTUS_TNT);

                        entries.add(ModItems.SPIKE_ARMOR_TRIM_SMITHING_TEMPLATE);

                    }).build());

    public static void registerItemGroups() {
        PricksnPlanks.LOGGER.info("Registering Mod Item Groups for " + PricksnPlanks.MOD_ID);
    }
}
