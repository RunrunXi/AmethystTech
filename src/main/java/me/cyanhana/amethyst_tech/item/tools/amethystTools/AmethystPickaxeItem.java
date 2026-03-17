package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class AmethystPickaxeItem extends PickaxeItem implements AmethystItem {

    public AmethystPickaxeItem() {
        super(ModToolTiers.AMETHYST, new Item.Properties()
                .attributes(PickaxeItem.createAttributes(ModToolTiers.AMETHYST, 1.0F, -2.8F)));
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
