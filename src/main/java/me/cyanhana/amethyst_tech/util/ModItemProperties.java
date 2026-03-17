package me.cyanhana.amethyst_tech.util;

import me.cyanhana.amethyst_tech.item.ModItems;
import me.cyanhana.amethyst_tech.item.tools.IModBow;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {

    public static void addCustomItemProperties() {
        registerBowProperties(ModItems.AMETHYST_BOW.get());
        registerBowProperties(ModItems.ECHO_BOW.get());
    }

    private static void registerBowProperties(Item bow) {
        // 1. pull 属性 - 拉弓进度 (0.0 - 1.0)
        ItemProperties.register(bow,
                ResourceLocation.withDefaultNamespace("pull"),
                (stack, level, entity, seed) -> {
                    if (entity == null) return 0.0F;

                    // 检查是否在使用中且拿的是这把弓
                    if (entity.getUseItem() != stack) return 0.0F;

                    // 计算拉弓进度
                    int useDuration = stack.getUseDuration(entity) - entity.getUseItemRemainingTicks();
                    double drawSpeedMulti = ((IModBow) stack.getItem()).getDrawSpeedMulti(stack);
                    float progress = (float) (useDuration * drawSpeedMulti) / 20.0F;  // 20 ticks = 1秒

                    // 限制在0-1之间
                    return Math.clamp(progress, 0.0F, 1.0F);
                }
        );

        // 2. pulling 属性 - 是否正在拉弓 (0.0 或 1.0)
        ItemProperties.register(bow,
                ResourceLocation.withDefaultNamespace("pulling"),
                (stack, level, entity, seed) -> {
                    return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
                }
        );
    }
}
