package com.portix.pricksnplanks.mixin;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeSkinningMixin {
    /*
    @Unique
    private static final Map<Block, Block> STRIPPED_CACTI = new ImmutableMap.Builder<Block, Block>()
        .put(Blocks.CACTUS, ModBlocks.SKINNED_CACTUS)
        .put(ModBlocks.SMOOTH_CACTUS, ModBlocks.SKINNED_CACTUS)
        .build();
     */

    /*
    @Unique
    private static final Map<Block, List<ItemStack>> CACTI_STRIP_DROPS = new ImmutableMap.Builder<Block, List<ItemStack>>()
            .put(Blocks.CACTUS, List.of(new ItemStack(Items.GREEN_DYE), new ItemStack(ModItems.CACTUS_SPIKES)))
            .put(ModBlocks.SMOOTH_CACTUS, List.of(new ItemStack(Items.GREEN_DYE)))
            .build();

     */

    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"), method = "tryStrip")
    public void changeCactusSound(World world, Entity source, BlockPos blockPos, SoundEvent sound, SoundCategory category, float volume, float pitch, Operation<Void> original) {
        Map<Block, Block> STRIPPED_CACTI = new ImmutableMap.Builder<Block, Block>()
                .put(Blocks.CACTUS, ModBlocks.SKINNED_CACTUS)
                .put(ModBlocks.SMOOTH_CACTUS, ModBlocks.SKINNED_CACTUS)
                .build();

        if (STRIPPED_CACTI.containsKey(world.getBlockState(blockPos).getBlock())) {
            original.call(world, source, blockPos, SoundEvents.BLOCK_CACTUS_FLOWER_BREAK, category, volume, pitch);
        } else {
            original.call(world, source, blockPos, sound, category, volume, pitch);
        }
    }

    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/AxeItem;getStrippedState(Lnet/minecraft/block/BlockState;)Ljava/util/Optional;"), method = "tryStrip")
    public Optional<BlockState> stripChange(AxeItem instance, BlockState blockState, Operation<Optional<BlockState>> original, World world, BlockPos pos, @Nullable PlayerEntity player, BlockState state) {
        Map<Block, Block> STRIPPED_CACTI = new ImmutableMap.Builder<Block, Block>()
                .put(Blocks.CACTUS, ModBlocks.SKINNED_CACTUS)
                .put(ModBlocks.SMOOTH_CACTUS, ModBlocks.SKINNED_CACTUS)
                .build();

        Map<Block, List<ItemStack>> CACTI_STRIP_DROPS = new ImmutableMap.Builder<Block, List<ItemStack>>()
                .put(Blocks.CACTUS, List.of(new ItemStack(Items.GREEN_DYE), new ItemStack(ModItems.CACTUS_SPIKES, world.random.nextBetween(1, 3))))
                .put(ModBlocks.SMOOTH_CACTUS, List.of(new ItemStack(Items.GREEN_DYE)))
                .build();

        Optional<BlockState> toReturn = Optional.empty();
        if (!original.call(instance, blockState).isPresent()) {
            toReturn = Optional.ofNullable((Block)STRIPPED_CACTI.get(blockState.getBlock()))
                    .map(Block::getDefaultState);
            if (toReturn.isPresent()) {
                Vec3d dropPos = pos.toCenterPos();
                if (CACTI_STRIP_DROPS.containsKey(blockState.getBlock())) {
                    for (ItemStack stack : CACTI_STRIP_DROPS.get(blockState.getBlock())) {
                        ItemEntity drop = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
                        world.spawnEntity(drop);
                    }
                }
            }
        }
        return toReturn;
    }
}
