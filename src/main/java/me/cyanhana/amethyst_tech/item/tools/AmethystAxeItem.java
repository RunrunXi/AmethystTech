package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.AxeItem;

public class AmethystAxeItem extends AxeItem {

    public AmethystAxeItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(AxeItem.createAttributes(ModToolTiers.AMETHYST, 5.0F, -3.1F)));
    }

}
