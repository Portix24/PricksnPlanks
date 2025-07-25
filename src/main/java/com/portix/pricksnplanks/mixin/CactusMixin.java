package com.portix.pricksnplanks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.portix.pricksnplanks.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CactusBlock.class)
public class CactusMixin {
	@WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"), method = "canPlaceAt")
	private boolean setReturnValue(BlockState blockState, Block block, Operation<Boolean> original) {
		return blockState.isIn(ModTags.Blocks.CACTUSES);
	}
}