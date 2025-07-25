package com.portix.pricksnplanks;

import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.effect.ModEffects;
import com.portix.pricksnplanks.entity.ModBlockEntityTypes;
import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.item.ModDecoratedPotPatterns;
import com.portix.pricksnplanks.item.ModItemGroups;
import com.portix.pricksnplanks.item.ModItems;
import com.portix.pricksnplanks.util.ModBlockSetTypes;
import com.portix.pricksnplanks.util.ModLootTableModifiers;
import com.portix.pricksnplanks.util.ModWoodTypes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
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
	}
}