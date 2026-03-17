package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.BowItem;

public class AmethystBowItem extends BowItem {
    public AmethystBowItem() {
        super(new Properties().durability(ModToolTiers.AMETHYST.getUses()));
    }
}
