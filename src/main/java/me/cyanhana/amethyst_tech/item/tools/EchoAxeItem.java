package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.hooks.IntrinsicEnchantItem;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class EchoAxeItem extends AxeItem implements IntrinsicEnchantItem {
    private final IntrinsicEnchantment intrinsicEnchantment = new IntrinsicEnchantment(Enchantments.SHARPNESS, 1);

    public EchoAxeItem() {
        super(ModToolTiers.ECHO, new Properties()
                .attributes(AxeItem.createAttributes(ModToolTiers.ECHO, 6.0F, -3.1F)));
    }

    @Override
    public int getIntrinsicEnchantLevel(ItemStack stack, Holder<Enchantment> enchantment) {
        return intrinsicEnchantment.getLevel(enchantment);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        intrinsicEnchantment.appendHoverText(context, tooltipComponents);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
