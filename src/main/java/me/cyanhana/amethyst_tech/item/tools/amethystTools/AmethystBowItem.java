package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.item.tools.BaseBowItem;
import me.cyanhana.amethyst_tech.item.tools.IModBow;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmethystBowItem extends BaseBowItem implements AmethystItem, IModBow {
    private static final double DRAW_SPEED_MULTI = 1.5;
    private static final double DAMAGE_MULTI = 0.8;

    public AmethystBowItem() {
        super(new Properties().durability(ModToolTiers.AMETHYST.getUses()));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        repairSelf(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        amethystToolHoverText(stack, tooltipComponents);
        tooltipComponents.add(Component.empty());
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
