package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public interface AmethystItem {

    default void amethystToolHoverText(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.tooltip.amethyst_tool_tooltip").withStyle(ChatFormatting.DARK_GRAY))
        );
    }

    default void repairSelf(ItemStack stack, Level level, Entity entity) {
        if (level.isClientSide || !stack.isDamaged()) return;
        if (stack.getItem() instanceof AmethystItem && entity instanceof LivingEntity) {
            // 每 2 秒有 30% 概率修复 1 点耐久 (期望速率: 0.15点/秒, 预计: 21.1分)
            if (level.getGameTime() % 40 == 0) {
                if (level.getRandom().nextInt(100) < 30) {
                    int oldDamage = stack.getDamageValue();
                    int newDamage = Math.max(0, stack.getDamageValue() - 1);
                    stack.setDamageValue(newDamage);
                }
            }
        }
    }
}
