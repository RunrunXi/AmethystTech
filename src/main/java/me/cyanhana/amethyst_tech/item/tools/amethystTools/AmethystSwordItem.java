package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.SwordItem;

public class AmethystSwordItem extends SwordItem {

    public AmethystSwordItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(SwordItem.createAttributes(ModToolTiers.AMETHYST, 3, -2.4F)));
    }

}
