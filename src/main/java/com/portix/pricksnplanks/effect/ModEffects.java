package com.portix.pricksnplanks.effect;

import com.portix.pricksnplanks.PricksnPlanks;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> THORNS = registerStatusEffect("thorns",
            new ThornsEffect(StatusEffectCategory.BENEFICIAL, 0xa39b72)
                    //.addAttributeModifier(EntityAttributes.ATTACK_KNOCKBACK, Identifier.of(PricksnPlanks.MOD_ID, "thorns"), 1.5, EntityAttributeModifier.Operation.ADD_VALUE)
            );

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(PricksnPlanks.MOD_ID, name), statusEffect);
    }

    public static void registerModEffects() {
        PricksnPlanks.LOGGER.info("Registering Mod Effects for " + PricksnPlanks.MOD_ID);
    }
}
