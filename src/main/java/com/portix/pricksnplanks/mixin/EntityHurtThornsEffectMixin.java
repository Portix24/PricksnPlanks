package com.portix.pricksnplanks.mixin;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EntityHurtThornsEffectMixin {
    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Inject(method = "damage", at = @At("TAIL"))
    private void checkThornsEffect(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity target = (LivingEntity) (Object) this;

        if (target.hasStatusEffect(ModEffects.THORNS)) {
            Entity attacker = source.getAttacker();
            if (attacker instanceof LivingEntity livingAttacker) {
                livingAttacker.damage(world, target.getDamageSources().thorns(target), amount / 2);
            }
        }
    }

}
