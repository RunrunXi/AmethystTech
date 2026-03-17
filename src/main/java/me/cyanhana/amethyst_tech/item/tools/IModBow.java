package me.cyanhana.amethyst_tech.item.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IModBow {
    default void bowModifierHoverText(ItemStack stack, List<Component> tooltipComponents) {
        // 拉弓速度
        float drawSpeedMulti = (float) getDrawSpeedMulti(stack);
        float bonusDamage = (float) getDamageMulti(stack);
        // 不为默认数值时
        if (drawSpeedMulti != 1.0F) {
            tooltipComponents.add(Component.empty()
                    .append(getPercentValue(drawSpeedMulti))
                    .append(Component.translatable("gui.amethyst_tech.tooltip.bow_draw_speed_multi").withStyle(ChatFormatting.BLUE))
            );
        }
        if (bonusDamage != 1.0F) {
            tooltipComponents.add(Component.empty()
                    .append(getPercentValue(bonusDamage))
                    .append(Component.translatable("gui.amethyst_tech.tooltip.bow_bonus_damage").withStyle(ChatFormatting.BLUE))
            );
        }
    }

    // 获取加成百分比
    private Component getPercentValue(float value) {
        float percent = (value - 1) * 100;
        int intPercent = Math.round(Math.abs(percent));  // 取绝对值
        ChatFormatting formatting = value > 1.0F ? ChatFormatting.BLUE : ChatFormatting.RED;
        String sign = value > 1.0F ? "+" : "-";
        return Component.literal(sign + intPercent + "%").withStyle(formatting);
    }

    default double getDrawSpeedMulti(ItemStack stack) {
        return 1.0F;
    }

    default double getDamageMulti(ItemStack stack) {
        return 1.0F;
    }

}