package com.portix.pricksnplanks.util;

import com.portix.pricksnplanks.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;

import java.util.List;
import java.util.Optional;

public class CactusTntExplosionBehavior extends AdvancedExplosionBehavior {
    private final boolean destroyBlocks;
    private final boolean damageEntities;
    private final Optional<Float> knockbackModifier;
    private final List<Block> exceptionBlocks;

    public CactusTntExplosionBehavior(boolean destroyBlocks, boolean damageEntities, Optional<Float> knockbackModifier, List<Block> exceptionBlocks) {
        super(destroyBlocks, damageEntities, knockbackModifier, Optional.empty());
        this.destroyBlocks = destroyBlocks;
        this.damageEntities = damageEntities;
        this.knockbackModifier = knockbackModifier;
        this.exceptionBlocks = exceptionBlocks;
    }

    @Override
    public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
        return this.destroyBlocks || exceptionBlocks.contains(state.getBlock());
    }
}
