package me.cyanhana.amethyst_tech.item;

import me.cyanhana.amethyst_tech.ModDataComponents;
import net.minecraft.world.item.ItemStack;

public interface EchoItem {
    int MAX_CHARGE = 500;
    default int getXpCharge(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.XP_CHARGE, 0);
    }
    default void setXpCharge(ItemStack stack, int value) {
        stack.set(ModDataComponents.XP_CHARGE, Math.min(value, MAX_CHARGE));
    }
}
