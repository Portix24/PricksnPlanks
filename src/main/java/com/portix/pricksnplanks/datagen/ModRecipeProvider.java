package com.portix.pricksnplanks.datagen;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.item.ModItems;
import com.portix.pricksnplanks.trim.ModTrimPatterns;
import com.portix.pricksnplanks.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                //offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);

                createShaped(RecipeCategory.MISC, ModBlocks.CACTUS_TNT)
                        .pattern("GCG")
                        .pattern("CGC")
                        .pattern("GCG")
                        .input('G', Items.GUNPOWDER)
                        .input('C', Blocks.CACTUS)
                        .criterion(hasItem(Blocks.CACTUS), conditionsFromItem(Blocks.CACTUS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.THROWABLE_CACTUS, 4)
                        .pattern(" S ")
                        .pattern("SCS")
                        .pattern(" S ")
                        .input('S', ModItems.CACTUS_SPIKES)
                        .input('C', ModBlocks.SMOOTH_CACTUS)
                        .criterion(hasItem(ModItems.CACTUS_SPIKES), conditionsFromItem(ModItems.CACTUS_SPIKES))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.FOOD, ModItems.CACTUS_STEW)
                        .input(Items.BOWL)
                        .input(ModTags.Items.DRY_GRASS)
                        .input(ModTags.Items.CACTUS_STEW_INGREDIENTS)
                        .criterion(hasItem(Blocks.CACTUS), conditionsFromItem(Blocks.CACTUS))
                        .offerTo(exporter);

                /*
                createShapeless(RecipeCategory.COMBAT, ModItems.THROWABLE_CACTUS, 4)
                        .input(ModBlocks.SMOOTH_CACTUS)
                        .input(ModItems.CACTUS_SPIKES)
                        .criterion(hasItem(ModItems.CACTUS_SPIKES), conditionsFromItem(ModItems.CACTUS_SPIKES))
                        .offerTo(exporter);
                 */

                createShapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CACTUS_PLANKS, 2)
                        .input(ModTags.Items.CACTUS_PLANK_CRAFTABLES)
                        .criterion(hasItem(Blocks.CACTUS), conditionsFromItem(Blocks.CACTUS))
                        .offerTo(exporter);

                createStairsRecipe(ModBlocks.CACTUS_PLANK_STAIRS, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CACTUS_PLANK_SLAB, ModBlocks.CACTUS_PLANKS);

                createDoorRecipe(ModBlocks.CACTUS_PLANK_DOOR, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);
                createTrapdoorRecipe(ModBlocks.CACTUS_PLANK_TRAPDOOR, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);

                createFenceRecipe(ModBlocks.CACTUS_PLANK_FENCE, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(ModBlocks.CACTUS_PLANK_FENCE_GATE, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);

                createButtonRecipe(ModBlocks.CACTUS_PLANK_BUTTON, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);
                offerPressurePlateRecipe(ModBlocks.CACTUS_PLANK_PRESSURE_PLATE, ModBlocks.CACTUS_PLANKS);

                createSignRecipe(ModItems.CACTUS_PLANK_SIGN_ITEM, Ingredient.ofItem(ModBlocks.CACTUS_PLANKS))
                        .criterion(hasItem(ModBlocks.CACTUS_PLANKS), conditionsFromItem(ModBlocks.CACTUS_PLANKS))
                        .offerTo(exporter);
                offerHangingSignRecipe(ModItems.CACTUS_PLANK_HANGING_SIGN_ITEM, ModBlocks.CACTUS_PLANKS);

                offerBoatRecipe(ModItems.CACTUS_BOAT, ModBlocks.CACTUS_PLANKS);
                offerChestBoatRecipe(ModItems.CACTUS_CHEST_BOAT, ModItems.CACTUS_BOAT);

                offerSmithingTrimRecipe(ModItems.SPINE_ARMOR_TRIM_SMITHING_TEMPLATE, ModTrimPatterns.SPINE, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(PricksnPlanks.MOD_ID, "spike")));
            }
        };
    }

    @Override
    public String getName() {
        return "Pricks 'n' Planks Recipes";
    }
}
