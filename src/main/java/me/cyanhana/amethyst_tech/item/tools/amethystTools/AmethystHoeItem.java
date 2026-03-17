package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.HoeItem;

public class AmethystHoeItem extends HoeItem {

    public AmethystHoeItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(HoeItem.createAttributes(ModToolTiers.AMETHYST, -3.0F, -1.0F)));
    }

}
