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
    @Inject(at = @At("RETURN"), method = "getItemEnchantmentLevel", cancellable = true)
    private static void hookGetItemEnchantmentLevel(Holder<Enchantment> enchantment, ItemStack stack,
                                                    CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof IntrinsicEnchantItem item) {
            int level = item.getIntrinsicEnchantLevel(stack, enchantment);
            if (level != 0) {
                // 与原版附魔等级可叠加
                int totalLevel = cir.getReturnValueI() + level;
                cir.setReturnValue(totalLevel);
            }
        }
    }
}