package me.cyanhana.amethyst_tech;

import me.cyanhana.amethyst_tech.helper.IntrinsicEnchantmentHelper;
import me.cyanhana.amethyst_tech.item.EchoItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;

import java.util.Optional;

@EventBusSubscriber(modid = AmethystTech.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void getXp(PlayerXpEvent.PickupXp e) {
        if (e.getEntity() instanceof ServerPlayer serverPlayer) {
            ExperienceOrb orb = e.getOrb();
            int orbValue = orb.getValue();
            int i = repairAndChargeItems(serverPlayer, orbValue);
            if (i <= 0) {
                e.setCanceled(true);
                orb.discard();
            }
        }
    }
    // 修复耐久
    private static int repairAndChargeItems(ServerPlayer player, int value) {
        // 获取当前装备的回响工具
        Optional<ItemStack> optional = IntrinsicEnchantmentHelper.getRandomItemStackWith(
                player,
                stack -> stack.getItem() instanceof EchoItem
        );
        if (optional.isPresent()) {
            ItemStack itemstack = optional.get();
            EchoItem echoItem = (EchoItem) itemstack.getItem();
            // 修复耐久
            if (itemstack.isDamaged()) {
                int i = EnchantmentHelper.modifyDurabilityToRepairFromXp(player.serverLevel(), itemstack, (int) (value * itemstack.getXpRepairRatio()));
                int j = Math.min(i, itemstack.getDamageValue());
                itemstack.setDamageValue(itemstack.getDamageValue() - j);
            }
            // 充能工具
            int currentXpCharge = echoItem.getXpCharge(itemstack);
            int newCharge = Math.min(currentXpCharge + value, EchoItem.MAX_CHARGE);
            echoItem.setXpCharge(itemstack, newCharge);

            int chargeGained = newCharge - currentXpCharge;
            return value - chargeGained;
        }
        return value;
    }


}
