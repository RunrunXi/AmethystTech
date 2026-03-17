package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmethystSwordItem extends SwordItem implements AmethystItem {

    public AmethystSwordItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(SwordItem.createAttributes(ModToolTiers.AMETHYST, 3, -2.4F)));
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
