package me.cyanhana.amethyst_tech.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import me.cyanhana.amethyst_tech.hooks.IntrinsicEnchantItem;

// 借鉴了应用能源2(AE2)的内置附魔思路, 并对其进行了一些修改
@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(at = @At("RETURN"), method = "getTagEnchantmentLevel", cancellable = true)
    private static void hookGetTagEnchantmentLevel(Holder<Enchantment> enchantment, ItemStack stack,
                                                    CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof IntrinsicEnchantItem item) {
            int level = item.getIntrinsicEnchantLevel(stack, enchantment);
            // 有魔咒时
            if (level != 0) {
                int totalLevel;
                // 魔咒最大等级不为 1 时叠加魔咒等级
                if (enchantment.value().getMaxLevel() != 1) {
                    totalLevel = cir.getReturnValueI() + level;
                } else {
                    totalLevel = 1;
                }
                cir.setReturnValue(totalLevel);
            }
        }
    }
}