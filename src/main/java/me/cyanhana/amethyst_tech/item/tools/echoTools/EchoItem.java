package me.cyanhana.amethyst_tech.item.tools.echoTools;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public interface EchoItem {
    // Attribute 修饰符的ID（使用ResourceLocation）
    ResourceLocation CHARGE_DAMAGE_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "echo_item_charge_damage");
    ResourceLocation CHARGE_MINE_EFFICIENCY_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "echo_item_charge_mine_efficiency");
    // 加成值
    float CHARGE_DAMAGE_BONUS = 1.0F;
    float CHARGE_EFFICIENCY_BONUS = 10.0F;
    // 充能条
    int MAX_CHARGE = 500;
    boolean isChargeBarVisible = true;

    default int getXpCharge(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.XP_CHARGE, 0);
    }
    default void setXpCharge(ItemStack stack, int value) {
        int clampedCharge = Math.clamp(value, 0, MAX_CHARGE);
        stack.set(ModDataComponents.XP_CHARGE, clampedCharge);
        enableAttributeModifier(stack, getXpCharge(stack) > 0);
    }
    default boolean hasXpCharge(ItemStack stack) {
        return getXpCharge(stack) > 0;
    }
    default int getXpChargeBarWidth(ItemStack stack) {
        return Math.round(13.0F * getXpCharge(stack) / EchoItem.MAX_CHARGE);
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
    default void enableAttributeModifier(ItemStack stack, boolean isEnable) {
        // 如果状态没有变化，提前返回，避免不必要的操作
        boolean currentlyEnabled = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS,
                        ItemAttributeModifiers.EMPTY)
                .modifiers().stream()
                .anyMatch(entry -> entry.modifier().id().equals(CHARGE_DAMAGE_MODIFIER_ID));

        if (currentlyEnabled == isEnable) {
            return;
        }
        // 获取当前的修饰符组件
        ItemAttributeModifiers currentModifiers =
                stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, stack.getItem().getDefaultAttributeModifiers(stack));
        // 创建新的修饰符构建器
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        currentModifiers.modifiers().forEach(entry -> {
            // 可以添加过滤逻辑，避免重复添加同ID的修饰符
            if (!entry.modifier().id().equals(CHARGE_DAMAGE_MODIFIER_ID)
                    && !entry.modifier().id().equals(CHARGE_MINE_EFFICIENCY_MODIFIER_ID)) {
                builder.add(entry.attribute(), entry.modifier(), entry.slot());
            }
        });
        if (isEnable) {
            // 添加新的修饰符
            builder.add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            CHARGE_DAMAGE_MODIFIER_ID,
                            CHARGE_DAMAGE_BONUS,
                            AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND);
            builder.add(
                    Attributes.MINING_EFFICIENCY,
                    new AttributeModifier(
                            CHARGE_MINE_EFFICIENCY_MODIFIER_ID,
                            CHARGE_EFFICIENCY_BONUS,
                            AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND);
        }
        // 设置更新后的修饰符组件
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
    }

    default void itemPostHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, int amount) {
        if (!hasXpCharge(stack)) {
            stack.hurtAndBreak(amount, attacker, EquipmentSlot.MAINHAND);
        } else {
            setXpCharge(stack, getXpCharge(stack) - 1);
        }
    }

    default boolean itemMineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        Tool tool = stack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                if (!hasXpCharge(stack)) {
                    stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
                } else {
                    setXpCharge(stack, getXpCharge(stack) - 1);
                }
            }
            return true;
        }
    }

}
