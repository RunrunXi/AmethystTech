package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmethystAxeItem extends AxeItem implements AmethystItem {

    public AmethystAxeItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(AxeItem.createAttributes(ModToolTiers.AMETHYST, 5.0F, -3.1F)));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        repairSelf(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        amethystToolHoverText(stack, tooltipComponents);
    }
}
