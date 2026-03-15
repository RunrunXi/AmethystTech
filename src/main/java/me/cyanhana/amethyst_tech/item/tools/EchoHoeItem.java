package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.item.EchoItem;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EchoHoeItem extends HoeItem implements EchoItem {

    public EchoHoeItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(HoeItem.createAttributes(ModToolTiers.ECHO, -2.0F, -1.0F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.echo_tool_tooltip")).withStyle(ChatFormatting.GRAY)
        );
    }
}
