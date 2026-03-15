package me.cyanhana.amethyst_tech.helper;

import net.minecraft.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class IntrinsicEnchantmentHelper {

    public static Optional<ItemStack> getRandomItemStackWith(LivingEntity entity, Predicate<ItemStack> filter) {
        List<ItemStack> list = new ArrayList<>();
        // 获取装备栏物品
        for (EquipmentSlot equipmentslot : EquipmentSlot.values()) {
            ItemStack itemstack = entity.getItemBySlot(equipmentslot);
            if (filter.test(itemstack)) {
                list.add(itemstack);
            }
        }

        return Util.getRandomSafe(list, entity.getRandom());
    }
}
