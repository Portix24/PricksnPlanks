package com.portix.pricksnplanks.util;

import com.portix.pricksnplanks.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier DESERT_WELL_ID =
            Identifier.of("minecraft", "archaeology/desert_well");

    private static final Identifier DESERT_PYRAMID_ID =
            Identifier.of("minecraft", "chests/desert_pyramid");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((registryKey, lootTableBuilder, tableSource, wrapperLookup) -> {
            if (DESERT_WELL_ID.equals(registryKey.getValue()) && tableSource.isBuiltin()) {
                //lootTableBuilder.pool(pool);   //this adds a pool

                lootTableBuilder.modifyPools(poolBuilder -> {
                    poolBuilder.with(ItemEntry.builder(ModItems.CACTUS_POTTERY_SHERD).weight(2));
                });
            }
        });

        LootTableEvents.MODIFY.register((registryKey, lootTableBuilder, tableSource, wrapperLookup) -> {
            if (DESERT_PYRAMID_ID.equals(registryKey.getValue()) && tableSource.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(EmptyEntry.builder().weight(6))      // 17:1 is as pigstep, but there's double-chests, and a lot more chests overall
                        .with(ItemEntry.builder(ModItems.THORNS_MUSIC_DISC).weight(1));     // 6:1 is as dune template

                lootTableBuilder.pool(pool);   //this adds a pool

                /*  // this modifies a pool
                lootTableBuilder.modifyPools(poolBuilder -> {
                    poolBuilder.with(ItemEntry.builder(ModItems.THORNS_MUSIC_DISC).weight(2));
                });
                 */
            }
        });
    }
}
