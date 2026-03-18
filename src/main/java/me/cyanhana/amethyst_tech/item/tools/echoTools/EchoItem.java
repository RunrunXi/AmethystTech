package me.cyanhana.amethyst_tech.item.tools.echoTools;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.util.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
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
    ResourceLocation CHARGE_ATTACK_SPEED_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "echo_item_charge_attack_speed");
    // 加成值
    float CHARGE_DAMAGE_BONUS = 1.0F;
    float CHARGE_EFFICIENCY_BONUS = 55.0F;
    float CHARGE_ATTACK_SPEED_BONUS = 0.5F;
    // 充能条
    int MAX_CHARGE = 500;

    default int getXpCharge(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.XP_CHARGE, 0);
    }
    default void setXpCharge(ItemStack stack, int value) {
        boolean oldEnableState = getXpCharge(stack) > 0;
        int clampedCharge = Math.clamp(value, 0, MAX_CHARGE);
        stack.set(ModDataComponents.XP_CHARGE, clampedCharge);
        update(stack, oldEnableState);
    }
    default void consumeXpCharge(ItemStack stack) {
        setXpCharge(stack, getXpCharge(stack) - 1);
    }
    default boolean hasXpCharge(ItemStack stack) {
        return getXpCharge(stack) > 0;
    }
    default int getXpChargeBarWidth(ItemStack stack) {
        return Math.round(13.0F * getXpCharge(stack) / EchoItem.MAX_CHARGE);
    }
    default void xpChargeHoverText(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable("gui.amethyst_tech.tooltip.echo_tool_tooltip_0").withStyle(ChatFormatting.DARK_GRAY));
        tooltipComponents.add(Component.translatable("gui.amethyst_tech.tooltip.echo_tool_tooltip_1").withStyle(ChatFormatting.DARK_GRAY));
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.tooltip.xp_charge").withStyle(ChatFormatting.GRAY))
                .append(" ")
                .append(Component.literal(stack.getOrDefault(ModDataComponents.XP_CHARGE, 0).toString()).withStyle(ChatFormatting.GREEN))
                .append(Component.literal("/").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(String.valueOf(MAX_CHARGE)).withStyle(ChatFormatting.GREEN))
        );
    }
    // 更新方法
    default void update(ItemStack stack, boolean oldEnableState) {

    }
    // 添加伤害修饰符
    default void setDamageAttributeModifier(ItemStack stack, boolean isEnable) {
        AttributeModifier modifier = new AttributeModifier(
                CHARGE_DAMAGE_MODIFIER_ID,
                CHARGE_DAMAGE_BONUS,
                AttributeModifier.Operation.ADD_VALUE
        );
        setAttributeModifier(stack, Attributes.ATTACK_DAMAGE, modifier, EquipmentSlotGroup.MAINHAND, isEnable);
    }
    // 添加挖掘效率修饰符
    default void setMineEfficiencyAttributeModifier(ItemStack stack, boolean isEnable) {
        AttributeModifier modifier = new AttributeModifier(
                CHARGE_MINE_EFFICIENCY_MODIFIER_ID,
                CHARGE_EFFICIENCY_BONUS,
                AttributeModifier.Operation.ADD_VALUE
        );
        setAttributeModifier(stack, Attributes.MINING_EFFICIENCY, modifier, EquipmentSlotGroup.MAINHAND, isEnable);
    }
    // 添加攻击速度修饰符
    default void setAttackSpeedAttributeModifier(ItemStack stack, boolean isEnable) {
        AttributeModifier modifier = new AttributeModifier(
                CHARGE_ATTACK_SPEED_MODIFIER_ID,
                CHARGE_ATTACK_SPEED_BONUS,
                AttributeModifier.Operation.ADD_VALUE
        );
        setAttributeModifier(stack, Attributes.ATTACK_SPEED, modifier, EquipmentSlotGroup.MAINHAND, isEnable);
    }

    /**
     * 设置工具的的属性修饰符
     *
     * @param stack 要修改的物品堆
     * @param attributes 要修改的属性
     * @param modifier 要设置的修饰符
     * @param slot 生效的装备槽位
     * @param isEnable true=添加修饰符，false=移除修饰符
     */
    default void setAttributeModifier(
            ItemStack stack, Holder<Attribute> attributes, AttributeModifier modifier, EquipmentSlotGroup slot, boolean isEnable
    ) {
        // 获取当前的修饰符组件
        ItemAttributeModifiers currentModifiers =
                stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, stack.getItem().getDefaultAttributeModifiers(stack));
        // 检查物品是否有匹配的组件
        boolean hasModifier = currentModifiers.modifiers().stream()
                .anyMatch(entry -> entry.modifier().id().equals(modifier.id()));
        // 如果状态没有变化，提前返回，避免不必要的操作
        if (hasModifier == isEnable) {
            return;
        }
        // 创建新的修饰符构建器
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        currentModifiers.modifiers().forEach(entry -> {
            // 过滤掉已有的修饰符, 如果需要加成才重新添加
            if (!entry.modifier().id().equals(modifier.id())) {
                builder.add(entry.attribute(), entry.modifier(), entry.slot());
            }
        });
        if (isEnable) {
            // 添加新的修饰符
            builder.add(attributes, modifier, slot);
        }
        // 设置更新后的修饰符组件
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
    }

    // 攻击后减少充能
    default void itemPostHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, int amount) {
        if (!hasXpCharge(stack)) {
            stack.hurtAndBreak(amount, attacker, EquipmentSlot.MAINHAND);
        } else {
            consumeXpCharge(stack);
        }
    }
    // 挖掘后减少充能
    default boolean itemMineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        Tool tool = stack.get(DataComponents.TOOL);
        if (tool == null) {
            return false;
        } else {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                if (!hasXpCharge(stack)) {
                    stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
                } else {
                    consumeXpCharge(stack);
                }
            }
            return true;
        }
    }

}
