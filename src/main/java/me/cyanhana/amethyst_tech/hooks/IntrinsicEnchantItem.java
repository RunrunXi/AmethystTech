package me.cyanhana.amethyst_tech.hooks;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 允许物品在特定附魔被检查的时候拥有“自带的”附魔等级。
 */
public interface IntrinsicEnchantItem {
    int getIntrinsicEnchantLevel(ItemStack stack, Holder<Enchantment> enchantment);
}