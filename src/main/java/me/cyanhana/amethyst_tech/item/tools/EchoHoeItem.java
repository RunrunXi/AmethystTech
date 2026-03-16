package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.item.EchoItem;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EchoHoeItem extends HoeItem implements EchoItem {

    public EchoHoeItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(HoeItem.createAttributes(ModToolTiers.ECHO, -4.0F, 0F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.echo_tool_tooltip")).withStyle(ChatFormatting.DARK_GRAY)
        );
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
