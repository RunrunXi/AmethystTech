package me.cyanhana.amethyst_tech;

import me.cyanhana.amethyst_tech.block.ModBlocks;
import me.cyanhana.amethyst_tech.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    // 注册创造模式标签页
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AmethystTech.MODID);

    public static final Supplier<CreativeModeTab> AMETHYST_TECH_TAB = CREATIVE_MODE_TAB.register("amethyst_tech_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.AMETHYST_SHARD))
                    .title(Component.translatable("creativetab.amethyst_tech.title"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // 添加创造模式标签页的物品
                        addItemsToTab(output);
                        // 添加创造模式标签页的方块
                        addBlocksToTab(output);
                    }).build());

    // 添加创造模式标签页的物品
    private static void addItemsToTab(CreativeModeTab.Output output) {
        // 紫晶工具
        output.accept(ModItems.AMETHYST_SWORD);
        output.accept(ModItems.AMETHYST_SHOVEL);
        output.accept(ModItems.AMETHYST_PICKAXE);
        output.accept(ModItems.AMETHYST_AXE);
        output.accept(ModItems.AMETHYST_HOE);
        // 回响工具
        output.accept(ModItems.ECHO_SWORD);
        output.accept(ModItems.ECHO_SHOVEL);
        output.accept(ModItems.ECHO_PICKAXE);
        output.accept(ModItems.ECHO_AXE);
        output.accept(ModItems.ECHO_HOE);
        // 锻造模板
        output.accept(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE);
        // 切割刀
        output.accept(ModItems.AMETHYST_CUTTING_KNIFE);
    }
    // 添加创造模式标签页的方块
    private static void addBlocksToTab(CreativeModeTab.Output output) {
        output.accept(ModBlocks.ECHO_BLOCK);
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
