package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.item.tools.BaseBowItem;
import me.cyanhana.amethyst_tech.item.tools.IModBow;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AmethystBowItem extends BaseBowItem implements IModBow {
    private static final double DRAW_SPEED_MULTI = 1.5;
    private static final double DAMAGE_MULTI = 0.8;

    public AmethystBowItem() {
        super(new Properties().durability(ModToolTiers.AMETHYST.getUses()));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        bowModifierHoverText(stack, tooltipComponents);
    }

    @Override
    public double getDrawSpeedMulti(ItemStack stack) {
        return DRAW_SPEED_MULTI;
    }
    @Override
    public double getDamageMulti(ItemStack stack) {
        return DAMAGE_MULTI;
    }
}
