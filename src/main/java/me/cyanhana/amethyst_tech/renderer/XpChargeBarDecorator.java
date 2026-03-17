package me.cyanhana.amethyst_tech.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import me.cyanhana.amethyst_tech.item.tools.echoTools.EchoItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;

public class XpChargeBarDecorator implements IItemDecorator {
    @Override
    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack stack, int x, int y) {
        if (!(stack.getItem() instanceof EchoItem echoItem)) {
            return false;
        }
        // 调整渲染层级
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(0, 0, 200);
        // 在耐久条下方渲染充能条
        int barWidth = stack.getBarWidth();
        int chargeWidth = echoItem.getXpChargeBarWidth(stack);
        int barHeight = stack.isDamaged()? 1 : 0;
        int barX = x + 2;
        int barY = y + 13 + barHeight;
        if (EchoItem.isChargeBarVisible) {
            // 背景
            guiGraphics.fill(barX, barY, barX + barWidth, barY + (stack.isDamaged()? 0 : 2), -16777216);
            // 充能条
            guiGraphics.fill(barX, barY, barX + chargeWidth, barY + 1, 0xFF44AAFF);
        }
        // 恢复渲染层级
        poseStack.popPose();
        return true;
    }

}
