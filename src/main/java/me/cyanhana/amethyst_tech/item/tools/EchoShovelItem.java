package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.item.EchoItem;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EchoShovelItem extends ShovelItem implements EchoItem {

    public EchoShovelItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(ShovelItem.createAttributes(ModToolTiers.ECHO, 1.5F, -3.0F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.echo_tool_tooltip")).withStyle(ChatFormatting.DARK_GRAY)
        );
        this.xpChargeHoverText(stack, tooltipComponents);
    }
}
