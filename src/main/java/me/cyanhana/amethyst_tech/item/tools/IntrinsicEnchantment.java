package me.cyanhana.amethyst_tech.item.tools;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

// 内置附魔类
final class IntrinsicEnchantment {
    private final ResourceKey<Enchantment> enchantment;
    private final int level;

    // 构造函数, 传入附魔名称和等级
    public IntrinsicEnchantment(ResourceKey<Enchantment> enchantment, int level) {
        this.enchantment = enchantment;
        this.level = level;
    }

    // Tooltip 工具提示 显示附魔名称和等级
    public void appendHoverText(Item.TooltipContext context, List<Component> tooltipComponents) {
        var registries = context.registries();
        if (registries == null) {
            return;
        }

        var registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        registrylookup.get(enchantment).ifPresent(holder -> {
            tooltipComponents.add(Component.empty()
                    .append(Component.translatable("gui.amethyst_tech.enchantment_tooltip"))
                    .append(" ")
                    .append(Enchantment.getFullname(holder, level))
            );
        });
    }

    // 获取附魔等级
    public int getLevel(Holder<Enchantment> enchantment) {
        return enchantment.is(this.enchantment) ? level : 0;
    }
}