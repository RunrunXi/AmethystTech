package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.ShovelItem;

public class AmethystShovelItem extends ShovelItem {

    public AmethystShovelItem() {
        super(ModToolTiers.AMETHYST, new Properties()
                .attributes(ShovelItem.createAttributes(ModToolTiers.AMETHYST, 1.5F, -3.0F)));
    }

}
