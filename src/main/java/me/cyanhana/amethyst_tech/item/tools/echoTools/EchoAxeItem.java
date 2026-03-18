package me.cyanhana.amethyst_tech.item.tools.echoTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EchoAxeItem extends AxeItem implements EchoItem {
    public EchoAxeItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(AxeItem.createAttributes(ModToolTiers.ECHO, 5.0F, -3.0F)));
    }

    @Override
    public void update(ItemStack stack, boolean oldEnableState) {
        boolean shouldEnable = getXpCharge(stack) > 0;

        // 只在状态真正变化时才更新属性组件
        if (shouldEnable != oldEnableState) {
            setDamageAttributeModifier(stack, shouldEnable);
            setMineEfficiencyAttributeModifier(stack, shouldEnable);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        this.xpChargeHoverText(stack, tooltipComponents);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        itemPostHurtEnemy(stack, target, attacker, 2);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        return itemMineBlock(stack, level, state, pos, miningEntity);
    }
}
