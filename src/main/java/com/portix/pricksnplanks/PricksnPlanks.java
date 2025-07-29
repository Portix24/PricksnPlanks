package com.portix.pricksnplanks;

import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.effect.ModEffects;
import com.portix.pricksnplanks.entity.ModBlockEntityTypes;
import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.entity.custom.CactusTntEntity;
import com.portix.pricksnplanks.item.ModDecoratedPotPatterns;
import com.portix.pricksnplanks.item.ModItemGroups;
import com.portix.pricksnplanks.item.ModItems;
import com.portix.pricksnplanks.util.ModBlockSetTypes;
import com.portix.pricksnplanks.util.ModLootTableModifiers;
import com.portix.pricksnplanks.util.ModWoodTypes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.event.GameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricksnPlanks implements ModInitializer {
	public static final String MOD_ID = "pricksnplanks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModWoodTypes.registerModWoodTypes();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

		ModEntityTypes.registerModEntities();
		ModBlockEntityTypes.registerModBlockEntities();

		ModBlockSetTypes.registerModBlockSetTypes();

		ModDecoratedPotPatterns.registerModDecoratedPotPatterns();

		ModLootTableModifiers.modifyLootTables();

		ModEffects.registerModEffects();

		DispenserBlock.registerProjectileBehavior(ModItems.THROWABLE_CACTUS);
		DispenserBlock.registerBehavior(ModBlocks.CACTUS_TNT, new FallibleItemDispenserBehavior() {
			@Override
			protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
				ServerWorld serverWorld = pointer.world();
				if (!serverWorld.getGameRules().getBoolean(GameRules.TNT_EXPLODES)) {
					this.setSuccess(false);
					return stack;
				} else {
					BlockPos blockPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
					CactusTntEntity cactusTntEntity = new CactusTntEntity(serverWorld, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, null);
					serverWorld.spawnEntity(cactusTntEntity);
					serverWorld.playSound(null, cactusTntEntity.getX(), cactusTntEntity.getY(), cactusTntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
					serverWorld.emitGameEvent(null, GameEvent.ENTITY_PLACE, blockPos);
					stack.decrement(1);
					this.setSuccess(true);
					return stack;
				}
			}
		});
		DispenserBlock.registerBehavior(ModItems.CACTUS_BOAT, new BoatDispenserBehavior(ModEntityTypes.CACTUS_BOAT));
		DispenserBlock.registerBehavior(ModItems.CACTUS_CHEST_BOAT, new BoatDispenserBehavior(ModEntityTypes.CACTUS_CHEST_BOAT));
	}
}