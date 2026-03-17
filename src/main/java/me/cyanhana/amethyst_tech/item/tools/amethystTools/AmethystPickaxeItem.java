package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;

public class AmethystPickaxeItem extends PickaxeItem {

    public AmethystPickaxeItem() {
        super(ModToolTiers.AMETHYST, new Item.Properties()
                .attributes(PickaxeItem.createAttributes(ModToolTiers.AMETHYST, 1.0F, -2.8F)));
    }

}
