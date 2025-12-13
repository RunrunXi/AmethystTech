package me.cyanhana.amethyst_tech.recipes.cutting;

import me.cyanhana.amethyst_tech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class CuttingRecipe implements CraftingRecipe {
    static int maxWidth = 3;
    static int maxHeight = 3;
    final String group;
    final CraftingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;

    public CuttingRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }
    // 配方合成返还
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        // 处理输入的物品并返回剩余物品, 返回的物品默认为空
        NonNullList<ItemStack> remainingItems = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        // 标记是否已处理过刀
        boolean damagedKnife = false;

        // 处理每一个输入的物品
        for (int i = 0; i < remainingItems.size(); i++) {
            ItemStack item = input.getItem(i);
            // 判断当前物品是否为切割刀
            if (!damagedKnife && item.is(ModTags.Items.CUTTING_KNIVES)) {
                damagedKnife = true;
                // 复制物品进行耐久计算
                ItemStack result = item.copy();
                MutableBoolean broken = new MutableBoolean(false);
                // 获取当前玩家并损坏物品
                if (CommonHooks.getCraftingPlayer() instanceof ServerPlayer serverPlayer) {
                    result.hurtAndBreak(1, serverPlayer.serverLevel(), serverPlayer, ignored -> broken.setTrue());
                } else {
                    var currentServer = ServerLifecycleHooks.getCurrentServer();
                    if (currentServer != null) {
                        result.hurtAndBreak(1, currentServer.overworld(), null, ignored -> broken.setTrue());
                    }
                }
                // 耐久损坏的刀返回空物品, 否则返回耐久损坏的刀
                remainingItems.set(i, broken.getValue() ? ItemStack.EMPTY : result);
            } else if (item.hasCraftingRemainingItem()) {
                // 处理非切割刀的物品
                remainingItems.set(i, item.getCraftingRemainingItem());
            }
        }
        return remainingItems;
    }

    // 配方类型为杂项
    @Override
    public CraftingBookCategory category() {
        return category;
    }
    // 从 ShapelessRecipe 复制
    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else if (!isSimple) {
            var nonEmptyItems = new java.util.ArrayList<ItemStack>(input.ingredientCount());
            for (var item : input.items())
                if (!item.isEmpty())
                    nonEmptyItems.add(item);
            return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        } else {
            if (input.size() == 1 && this.ingredients.size() == 1) {
                return this.ingredients.getFirst().test(input.getItem(0));
            }
            return input.stackedContents().canCraft(this, null);
        }
    }
    // 从 ShapelessRecipe 复制
    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }
    // 从 ShapelessRecipe 复制
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }
    // 从 ShapelessRecipe 复制
    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return CuttingRecipeSerializer.INSTANCE;
    }
}
