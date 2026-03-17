package me.cyanhana.amethyst_tech.event;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.item.ModItems;
import me.cyanhana.amethyst_tech.item.tools.IModBow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

@EventBusSubscriber(modid = AmethystTech.MODID, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        // 获取玩家
        Player player = event.getPlayer();
        if (!player.isUsingItem()) return;
        // 获取物品
        ItemStack usingStack = player.getUseItem();
        if (usingStack.isEmpty()) return;  // 防止空物品
        // 拉弓时拉伸视角
        if (event.getPlayer().isUsingItem() && (usingStack.getItem() instanceof IModBow bowItem)
        ) {
            // 获取拉弓速度
            double multi = bowItem.getDrawSpeedMulti(usingStack);
            float fovModifier = 1.0F;
            int ticksUsingItem = player.getTicksUsingItem();
            float deltaTicks = (float) (ticksUsingItem * multi) / 20.0F;
            if (deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1.0F - deltaTicks * 0.15F;
            event.setNewFovModifier(fovModifier);
        }
    }

}
