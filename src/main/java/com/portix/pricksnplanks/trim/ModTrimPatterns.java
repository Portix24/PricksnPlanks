package com.portix.pricksnplanks.trim;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> SPIKE = RegistryKey.of(RegistryKeys.TRIM_PATTERN, Identifier.of(PricksnPlanks.MOD_ID, "spike"));

    public static void bootstrap(Registerable<ArmorTrimPattern> context) {
        register(context, ModItems.SPIKE_ARMOR_TRIM_SMITHING_TEMPLATE, SPIKE);
    }

    private static void register(Registerable<ArmorTrimPattern> context, Item item, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern trimPattern = new ArmorTrimPattern(key.getValue(),
                Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);
        context.register(key, trimPattern);
    }
}
