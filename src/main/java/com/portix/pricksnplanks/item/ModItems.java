package com.portix.pricksnplanks.item;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.item.custom.ThrowableCactusItem;
import com.portix.pricksnplanks.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {
    public static final Item THROWABLE_CACTUS = registerItem("throwable_cactus",
            ThrowableCactusItem::new, new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)
            );

    public static final Item CACTUS_SPIKES = registerItem("cactus_spikes",
            Item::new, new Item.Settings());

    public static final Item CACTUS_PLANK_SIGN_ITEM = registerSign(
            "cactus_plank_sign_item",    //_item
            SignItem::new, ModBlocks.CACTUS_PLANK_SIGN, ModBlocks.CACTUS_PLANK_WALL_SIGN,
            new Item.Settings().maxCount(16)
    );

    public static final Item CACTUS_PLANK_HANGING_SIGN_ITEM = registerSign(
            "cactus_plank_hanging_sign_item",    //_item
            HangingSignItem::new, ModBlocks.CACTUS_PLANK_HANGING_SIGN, ModBlocks.CACTUS_PLANK_WALL_HANGING_SIGN,
            new Item.Settings().maxCount(16)
    );

    public static final Item CACTUS_BOAT = registerItem(
            "cactus_boat",
            settings -> new BoatItem(ModEntityTypes.CACTUS_BOAT, settings),
            new Item.Settings().maxCount(1)
    );

    public static final Item CACTUS_CHEST_BOAT = registerItem(
            "cactus_chest_boat",
            settings -> new BoatItem(ModEntityTypes.CACTUS_CHEST_BOAT, settings),
            new Item.Settings().maxCount(1)
    );

    public static final Item CACTUS_POTTERY_SHERD = registerItem("cactus_pottery_sherd",
            Item::new, new Item.Settings().rarity(Rarity.UNCOMMON));

    public static final Item CACTUS_STEW = registerItem("cactus_stew",
            Item::new,
            new Item.Settings().rarity(Rarity.UNCOMMON)
                    .food(ModFoodComponents.CACTUS_STEW, ModConsumableComponents.CACTUS_STEW)
                    .useRemainder(Items.BOWL)
                    .maxCount(16));

    public static final Item THORNS_MUSIC_DISC = registerItem("thorns_music_disc",
            Item::new, new Item.Settings().jukeboxPlayable(ModSounds.THORNS_KEY).maxCount(1).rarity(Rarity.UNCOMMON));

    public static final Item SPINE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("spine_armor_trim_smithing_template",
            SmithingTemplateItem::of, new Item.Settings().rarity(Rarity.UNCOMMON));


    private static Item registerSign(String name, TriFunction<Block, Block, Item.Settings, Item> function, Block mainSign, Block wallSign, Item.Settings settings) {
        Identifier identifier = Identifier.of(PricksnPlanks.MOD_ID, name);
        Item toRegister = function.apply(mainSign, wallSign, settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier)));
        return Registry.register(Registries.ITEM, identifier, toRegister);
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> function, Item.Settings settings) {
        Item toRegister = function.apply(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PricksnPlanks.MOD_ID, name))));
        return Registry.register(Registries.ITEM, Identifier.of(PricksnPlanks.MOD_ID, name), toRegister);
    }

    public static void registerModItems() {
        PricksnPlanks.LOGGER.info("Registering Mod Items for " + PricksnPlanks.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(THROWABLE_CACTUS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CACTUS_SPIKES);
            entries.add(CACTUS_POTTERY_SHERD);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(CACTUS_STEW);
        });
    }
}
