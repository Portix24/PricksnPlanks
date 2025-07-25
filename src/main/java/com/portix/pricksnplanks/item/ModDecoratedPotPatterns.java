package com.portix.pricksnplanks.item;

import com.portix.pricksnplanks.PricksnPlanks;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDecoratedPotPatterns {
    public static final RegistryKey<DecoratedPotPattern> CACTUS = registerKey("cactus");
    //public static final DecoratedPotPattern CACTUS_POT_PATTERN = registerPattern(CACTUS, "cactus_pottery_pattern");

    public static RegistryKey<DecoratedPotPattern> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, Identifier.of(PricksnPlanks.MOD_ID, name));
    }

    public static DecoratedPotPattern registerPattern(RegistryKey<DecoratedPotPattern> key, String name) {
        return Registry.register(Registries.DECORATED_POT_PATTERN, key, new DecoratedPotPattern(Identifier.of(PricksnPlanks.MOD_ID, name)));
    }

    public static void registerModDecoratedPotPatterns() {
        PricksnPlanks.LOGGER.info("Registering Mod Decorated Pot Patterns for " + PricksnPlanks.MOD_ID);
    }
}
