package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.item.EchoItem;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EchoPickaxeItem extends PickaxeItem implements EchoItem {

    public EchoPickaxeItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(PickaxeItem.createAttributes(ModToolTiers.ECHO, 1.0F, -2.8F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.empty()
                .append(Component.translatable("gui.amethyst_tech.echo_tool_tooltip")).withStyle(ChatFormatting.GRAY)
        );
    }

}
