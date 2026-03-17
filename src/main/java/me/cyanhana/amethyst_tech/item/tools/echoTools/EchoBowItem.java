package me.cyanhana.amethyst_tech.item.tools.echoTools;

import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.world.item.BowItem;

public class EchoBowItem extends BowItem {
    public EchoBowItem() {
        super(new Properties().durability(ModToolTiers.ECHO.getUses()));
    }
}
