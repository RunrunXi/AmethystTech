package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.item.EchoItem;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EchoSwordItem extends SwordItem implements EchoItem {

    public EchoSwordItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(SwordItem.createAttributes(ModToolTiers.ECHO, 3, -2.4F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.echo_tool_tooltip")).withStyle(ChatFormatting.GRAY)
        );
    }
}
