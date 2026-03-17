package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmethystShovelItem extends ShovelItem implements AmethystItem {

    public AmethystShovelItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(ShovelItem.createAttributes(ModToolTiers.AMETHYST, 1.5F, -3.0F)));
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
