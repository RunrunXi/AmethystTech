package me.cyanhana.amethyst_tech.item.tools.echoTools;

import me.cyanhana.amethyst_tech.item.tools.BaseBowItem;
import me.cyanhana.amethyst_tech.item.tools.IModBow;
import me.cyanhana.amethyst_tech.util.ModToolTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EchoBowItem extends BaseBowItem implements EchoItem, IModBow {
    private static final double DRAW_SPEED_MULTI = 1.2;
    private static final double DAMAGE_MULTI = 1.1;

    public EchoBowItem() {
        super(new Properties().durability(ModToolTiers.ECHO.getUses()));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        xpChargeHoverText(stack, tooltipComponents);
        tooltipComponents.add(Component.empty());
        bowModifierHoverText(stack, tooltipComponents);
    }

    @Override
    public void onShoot(ItemStack stack) {
        super.onShoot(stack);
        consumeXpCharge(stack);
    }

    @Override
    public void update(ItemStack stack) {

    }

    @Override
    public double getDrawSpeedMulti(ItemStack stack) {
        return DRAW_SPEED_MULTI + (hasXpCharge(stack) ? 0.2 : 0);
    }

    @Override
    public double getDamageMulti(ItemStack stack) {
        return DAMAGE_MULTI + (hasXpCharge(stack) ? 0.1 : 0);
    }
}
