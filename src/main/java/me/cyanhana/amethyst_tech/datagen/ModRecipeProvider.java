package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.block.ModBlocks;
import me.cyanhana.amethyst_tech.item.ModItems;
import me.cyanhana.amethyst_tech.recipes.cutting.CuttingRecipeBuilder;
import me.cyanhana.amethyst_tech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // 有序配方
        registerShapedRecipe(recipeOutput);
        // 无序配方
        registerShapelessRecipe(recipeOutput);
        // 锻造配方
        registerSmithingRecipe(recipeOutput);
        // 切割配方
        registerCuttingRecipe(recipeOutput);
    }

    // 有序配方
    private void registerShapedRecipe(RecipeOutput recipeOutput) {
        // 回响块
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ECHO_BLOCK)
                .pattern("##")
                .pattern("##")
                .define('#', Items.ECHO_SHARD)
                .unlockedBy("has_echo_shard", has(Items.ECHO_SHARD))
                .save(recipeOutput);
        // 回响升级锻造模板
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("dUd")
                .pattern("d#d")
                .pattern("ddd")
                .define('d', Items.DIAMOND)
                .define('U', ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE)
                .define('#', Blocks.COBBLED_DEEPSLATE)
                .unlockedBy("has_echo_upgrade_smithing_template", has(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE))
                .save(recipeOutput);
        // 紫晶切割刀
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMETHYST_CUTTING_KNIFE)
                .pattern("D")
                .pattern("A")
                .pattern("S")
                .define('D', Items.DIAMOND)
                .define('A', Items.AMETHYST_SHARD)
                .define('S', Items.STICK)
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);
    }

    // 无序配方
    private void registerShapelessRecipe(RecipeOutput recipeOutput) {
        // 回响碎片
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ECHO_SHARD, 4)
                .requires(ModBlocks.ECHO_BLOCK)
                .unlockedBy("has_echo_block", has(ModBlocks.ECHO_BLOCK))
                .save(recipeOutput, AmethystTech.MODID + ":" + "echo_shard_from_block");
        // 紫水晶碎片
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.AMETHYST_SHARD, 4)
                .requires(Blocks.AMETHYST_BLOCK)
                .unlockedBy("has_amethyst_block", has(Blocks.AMETHYST_BLOCK))
                .save(recipeOutput, AmethystTech.MODID + ":" + "amethyst_shard_from_block");
    }

    // 切割配方
    private void registerCuttingRecipe(RecipeOutput recipeOutput) {
        CuttingRecipeBuilder.cutting(RecipeCategory.MISC, ModItems.IRON_PLATE, 9)
                .requires(ModTags.Items.CUTTING_KNIVES)
                .requires(Blocks.IRON_BLOCK)
                .unlockedBy("has_cutting_knife", has(ModTags.Items.CUTTING_KNIVES))
                .save(recipeOutput, AmethystTech.MODID + ":" + "iron_plate_from_block");
        CuttingRecipeBuilder.cutting(RecipeCategory.MISC, ModItems.COPPER_PLATE, 9)
                .requires(ModTags.Items.CUTTING_KNIVES)
                .requires(Blocks.COPPER_BLOCK)
                .unlockedBy("has_cutting_knife", has(ModTags.Items.CUTTING_KNIVES))
                .save(recipeOutput, AmethystTech.MODID + ":" + "copper_plate_from_block");
        CuttingRecipeBuilder.cutting(RecipeCategory.MISC, ModItems.GOLD_PLATE, 9)
                .requires(ModTags.Items.CUTTING_KNIVES)
                .requires(Blocks.GOLD_BLOCK)
                .unlockedBy("has_cutting_knife", has(ModTags.Items.CUTTING_KNIVES))
                .save(recipeOutput, AmethystTech.MODID + ":" + "gold_plate_from_block");
    }

    // 锻造配方
    private void registerSmithingRecipe(RecipeOutput recipeOutput) {
        echoSmithing(recipeOutput, ModItems.AMETHYST_SWORD.get(), RecipeCategory.COMBAT, ModItems.ECHO_SWORD.get());
        echoSmithing(recipeOutput, ModItems.AMETHYST_SHOVEL.get(), RecipeCategory.TOOLS, ModItems.ECHO_SHOVEL.get());
        echoSmithing(recipeOutput, ModItems.AMETHYST_PICKAXE.get(), RecipeCategory.TOOLS, ModItems.ECHO_PICKAXE.get());
        echoSmithing(recipeOutput, ModItems.AMETHYST_AXE.get(), RecipeCategory.TOOLS, ModItems.ECHO_AXE.get());
        echoSmithing(recipeOutput, ModItems.AMETHYST_HOE.get(), RecipeCategory.TOOLS, ModItems.ECHO_HOE.get());
    }

    // 回响锻造配方模板
    protected static void echoSmithing(RecipeOutput recipeOutput, Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ingredientItem), Ingredient.of(ModBlocks.ECHO_BLOCK),
                        category, resultItem
                )
                .unlocks("has_echo_block", has(ModBlocks.ECHO_BLOCK))
                .save(recipeOutput, AmethystTech.MODID + ":" + getItemName(resultItem) + "_smithing");
    }
}
