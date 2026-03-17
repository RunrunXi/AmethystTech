package me.cyanhana.amethyst_tech.item.tools.echoTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EchoSwordItem extends SwordItem implements EchoItem {

    public EchoSwordItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(SwordItem.createAttributes(ModToolTiers.ECHO, 3, -2.4F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        this.xpChargeHoverText(stack, tooltipComponents);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        itemPostHurtEnemy(stack, target, attacker, 1);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        return itemMineBlock(stack, level, state, pos, miningEntity);
    }
}
