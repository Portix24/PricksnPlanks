package com.portix.pricksnplanks.mixin;

import com.google.common.collect.ImmutableMap;
import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(ShearsItem.class)
public class ShearsSmoothingMixin {
    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void shearChange(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        Map<Block, Block> SMOOTHED_CACTI = new ImmutableMap.Builder<Block, Block>()
                .put(Blocks.CACTUS, ModBlocks.SMOOTH_CACTUS)
                .build();

        Map<Block, List<ItemStack>> CACTI_SMOOTH_DROPS = new ImmutableMap.Builder<Block, List<ItemStack>>()
                .put(Blocks.CACTUS, List.of(new ItemStack(ModItems.CACTUS_SPIKES, world.random.nextBetween(1, 3))))
                .build();

        if (SMOOTHED_CACTI.containsKey(blockState.getBlock())) {
            PlayerEntity playerEntity = context.getPlayer();
            ItemStack itemStack = context.getStack();
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }

            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);

            BlockState newBlockState = SMOOTHED_CACTI.get(blockState.getBlock()).getDefaultState();

            world.setBlockState(blockPos, newBlockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(context.getPlayer(), newBlockState));

            if (CACTI_SMOOTH_DROPS.containsKey(blockState.getBlock())) {
                Vec3d dropPos = blockPos.toCenterPos();
                for (ItemStack stack : CACTI_SMOOTH_DROPS.get(blockState.getBlock())) {
                    ItemEntity drop = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
                    world.spawnEntity(drop);
                }
            }

            /*
            Vec3d dropPos = blockPos.toCenterPos();
            ItemEntity drop = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, new ItemStack(ModItems.CACTUS_SPIKES));
            world.spawnEntity(drop);
             */

            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
            }

            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
