package me.cyanhana.amethyst_tech;

import me.cyanhana.amethyst_tech.helper.IntrinsicEnchantmentHelper;
import me.cyanhana.amethyst_tech.item.EchoItem;
import net.minecraft.server.level.ServerPlayer;
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
            int orbValue = e.getOrb().getValue();
            repairPlayerItems(serverPlayer, orbValue);
        }

    }
    private static void repairPlayerItems(ServerPlayer player, int value) {
        Optional<ItemStack> optional = IntrinsicEnchantmentHelper.getRandomItemStackWith(player, ItemStack::isDamaged);
        if (optional.isPresent()) {
            ItemStack itemstack = optional.get();
            Item item = itemstack.getItem();
//            if (item instanceof IntrinsicEnchantItem intrinsicEnchantItem) {
//                intrinsicEnchantItem.getIntrinsicEnchantLevel(itemstack, Enchantments.MENDING);
//            }
            if (item instanceof EchoItem)
            {
                int i = EnchantmentHelper.modifyDurabilityToRepairFromXp(player.serverLevel(), itemstack, (int) (value * itemstack.getXpRepairRatio()));
                int j = Math.min(i, itemstack.getDamageValue());
                itemstack.setDamageValue(itemstack.getDamageValue() - j);
            }

        }
    }

}
