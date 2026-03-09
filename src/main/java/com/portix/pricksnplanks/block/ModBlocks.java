package com.portix.pricksnplanks.block;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.block.custom.*;
import com.portix.pricksnplanks.util.ModBlockSetTypes;
import com.portix.pricksnplanks.util.ModWoodTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block SMOOTH_CACTUS = registerBlock("smooth_cactus",
            SmoothCactusBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.4F)
                    .sounds(BlockSoundGroup.WOOL)
                    .pistonBehavior(PistonBehavior.DESTROY),
                    //.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, "smooth_cactus"))),
            new Item.Settings()
    );

    public static final Block SKINNED_CACTUS = registerBlock("skinned_cactus",
            SkinnedCactusBlock::new, AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.4F)
                    .sounds(BlockSoundGroup.WOOL)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    //.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, "skinned_cactus")))
            );

    public static final Block CACTUS_PLANKS = registerBlock("cactus_planks",
            Block::new, AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
            );

    public static final Block CACTUS_PLANK_STAIRS = registerStairsBlock("cactus_plank_stairs", ModBlocks.CACTUS_PLANKS);
    public static final Block CACTUS_PLANK_SLAB = registerBlock("cactus_plank_slab", SlabBlock::new, AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS));

    public static final Block CACTUS_PLANK_PRESSURE_PLATE = registerBlock("cactus_plank_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetTypes.CACTUS, properties), AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS));
    public static final Block CACTUS_PLANK_BUTTON = registerBlock("cactus_plank_button", properties -> new ButtonBlock(ModBlockSetTypes.CACTUS, 30, properties), AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS));

    public static final Block CACTUS_PLANK_FENCE = registerBlock("cactus_plank_fence", FenceBlock::new, AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS));
    public static final Block CACTUS_PLANK_FENCE_GATE = registerBlock("cactus_plank_fence_gate", properties -> new FenceGateBlock(WoodType.BAMBOO, properties), AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS));

    public static final Block CACTUS_PLANK_DOOR = registerBlock("cactus_plank_door", properties -> new DoorBlock(ModBlockSetTypes.CACTUS, properties), AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS).nonOpaque());
    public static final Block CACTUS_PLANK_TRAPDOOR = registerBlock("cactus_plank_trapdoor", properties -> new TrapdoorBlock(ModBlockSetTypes.CACTUS, properties), AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS).nonOpaque());

    public static final Block CACTUS_PLANK_SIGN = registerBlockWithoutItem(
            "cactus_plank_sign",
            properties -> new CactusSignBlock(ModWoodTypes.CACTUS, properties),
            AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS).solid().noCollision().strength(1.0F));

    public static final Block CACTUS_PLANK_WALL_SIGN = registerBlockWithoutItem(
            "cactus_plank_wall_sign",
            settings -> new CactusWallSignBlock(ModWoodTypes.CACTUS, settings),
            copyLootTable(CACTUS_PLANK_SIGN, true)
    );

    public static final Block CACTUS_PLANK_HANGING_SIGN = registerBlockWithoutItem("cactus_plank_hanging_sign",
            properties -> new CactusHangingSignBlock(ModWoodTypes.CACTUS, properties),
            AbstractBlock.Settings.copy(ModBlocks.CACTUS_PLANKS).solid().noCollision().strength(1.0F));

    public static final Block CACTUS_PLANK_WALL_HANGING_SIGN = registerBlockWithoutItem(
            "cactus_plank_wall_hanging_sign",
            settings -> new CactusWallHangingSignBlock(ModWoodTypes.CACTUS, settings),
            copyLootTable(CACTUS_PLANK_HANGING_SIGN, true)
    );

    public static final Block CACTUS_TNT = registerBlock("cactus_tnt",
            CactusTntBlock::new, AbstractBlock.Settings.copy(Blocks.TNT)
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.WOOL)
    );

    private static AbstractBlock.Settings copyLootTable(Block block, boolean copyTranslationKey) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(block);
        AbstractBlock.Settings settings2 = settings.lootTable(block.getLootTableKey());
        if (copyTranslationKey) {
            settings2 = settings2.overrideTranslationKey(block.getTranslationKey());
        }

        return settings2;
    }

    private static Block registerStairsBlock(String id, Block base) {
        return registerBlock(id, settings -> new StairsBlock(base.getDefaultState(), settings), AbstractBlock.Settings.copy(base));
    }

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function, AbstractBlock.Settings settings, Item.Settings itemSettings) {
        Block toRegister = function.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, name))));
        registerBlockItem(name, toRegister, itemSettings);
        return Registry.register(Registries.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, name), toRegister);
    }

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function, AbstractBlock.Settings settings) {
        return registerBlock(name, function, settings, new Item.Settings());
    }

    private static Block registerBlockWithoutItem(String name, Function<AbstractBlock.Settings, Block> function, AbstractBlock.Settings settings) {
        Block toRegister = function.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, name))));
        return Registry.register(Registries.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, name), toRegister);
    }

    private static Item registerBlockItem(String name, Block block, Item.Settings settings) {
        //PricksnPlanks.LOGGER.info("Registering block item for {}", name);
        Item registeredItem = Registry.register(Registries.ITEM, Identifier.of(PricksnPlanks.MOD_ID, name),
                new BlockItem(block, settings.useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PricksnPlanks.MOD_ID, name)))
                ));
        BlockItem.BLOCK_ITEMS.put(block, registeredItem);
        return registeredItem;
    }

    public static void registerModBlocks() {
        PricksnPlanks.LOGGER.info("Registering Mod Blocks for " + PricksnPlanks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            //entries.add(SMOOTH_CACTUS_ITEM);
            //entries.add(SKINNED_CACTUS_ITEM);
            entries.add(SMOOTH_CACTUS);
            entries.add(SKINNED_CACTUS);

            entries.add(CACTUS_PLANKS);

            entries.add(CACTUS_PLANK_SLAB);
            entries.add(CACTUS_PLANK_STAIRS);
            entries.add(CACTUS_PLANK_BUTTON);
            entries.add(CACTUS_PLANK_PRESSURE_PLATE);
            entries.add(CACTUS_PLANK_FENCE);
            entries.add(CACTUS_PLANK_FENCE_GATE);
            entries.add(CACTUS_PLANK_DOOR);
            entries.add(CACTUS_PLANK_TRAPDOOR);

            entries.add(CACTUS_PLANK_HANGING_SIGN);
            entries.add(CACTUS_PLANK_SIGN);
        });
    }
}
