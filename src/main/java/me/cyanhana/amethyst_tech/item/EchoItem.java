package me.cyanhana.amethyst_tech.item;

import me.cyanhana.amethyst_tech.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface EchoItem {
    int MAX_CHARGE = 500;
    default int getXpCharge(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.XP_CHARGE, 0);
    }
    default void setXpCharge(ItemStack stack, int value) {
        stack.set(ModDataComponents.XP_CHARGE, Math.min(value, MAX_CHARGE));
    }
    default void xpChargeHoverText(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.xp_charge").withStyle(ChatFormatting.GRAY))
                .append(" ")
                .append(Component.literal(stack.getOrDefault(ModDataComponents.XP_CHARGE, 0).toString()).withStyle(ChatFormatting.GREEN))
                .append(Component.literal("/").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(String.valueOf(MAX_CHARGE)).withStyle(ChatFormatting.GREEN))
        );
    }
}
