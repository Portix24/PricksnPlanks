package com.portix.pricksnplanks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.item.ModDecoratedPotPatterns;
import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.block.DecoratedPotPatterns;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternAttempt {

    /*
    @Unique
    private static final Map<Item, RegistryKey<DecoratedPotPattern>> MOD_SHERD_TO_PATTERN = Map.ofEntries(
            Map.entry(ModItems.CACTUS_POTTERY_SHERD, ModDecoratedPotPatterns.CACTUS)
    );
     */

    @Inject(method = "registerAndGetDefault", at = @At("HEAD"))
    private static void injectPatternRegistration(Registry<DecoratedPotPattern> registry, CallbackInfoReturnable<DecoratedPotPattern> cir) {
        Registry.register(registry, ModDecoratedPotPatterns.CACTUS, new DecoratedPotPattern(Identifier.of(PricksnPlanks.MOD_ID, "cactus_pottery_pattern")));
    }

    @WrapOperation(method = "fromSherd", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private static Object modifiedFromSherd(Map<Item, RegistryKey<DecoratedPotPattern>> originalMap, Object key, Operation<RegistryKey<DecoratedPotPattern>> originalOperation) {
        Map<Item, RegistryKey<DecoratedPotPattern>> MOD_SHERD_TO_PATTERN = Map.ofEntries(
                Map.entry(ModItems.CACTUS_POTTERY_SHERD, ModDecoratedPotPatterns.CACTUS)
        );
        Item sherd = (Item) key;

        return originalOperation.call(
                originalMap.containsKey(sherd) ? originalMap : MOD_SHERD_TO_PATTERN,
                sherd
        );
    }
}
