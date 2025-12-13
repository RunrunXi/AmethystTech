package me.cyanhana.amethyst_tech.item;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.item.tools.*;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/* 要添加一个物品，有以下步骤:
* 1. 创建一个DeferredItem<Item>
* 2. 添加到创造模式标签页
* 3. 填写生成器: 物品模型, 物品标签(如有需要)
* 4. 添加材质
* 5. 添加翻译文件
* */
public class ModItems {
    // 延迟注册物品
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AmethystTech.MODID);
    // 注册物品
    public static final DeferredItem<Item> ECHO_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("echo_upgrade_smithing_template", EchoUpgradeSmithingTemplateItem::new);
    // 紫晶工具
    // 工具特性: 铁的属性, 铜的耐久, 金的附魔值, 钻石的伤害
    public static final DeferredItem<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", AmethystSwordItem::new);
    public static final DeferredItem<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", AmethystShovelItem::new);
    public static final DeferredItem<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", AmethystPickaxeItem::new);
    public static final DeferredItem<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", AmethystAxeItem::new);
    public static final DeferredItem<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", AmethystHoeItem::new);
    // 切割刀
    public static final DeferredItem<Item> AMETHYST_CUTTING_KNIFE = ITEMS.register("amethyst_cutting_knife", AmethystCuttingKnifeItem::new);
    // 回响工具
    public static final DeferredItem<Item> ECHO_SWORD = ITEMS.register("echo_sword", EchoSwordItem::new);
    public static final DeferredItem<Item> ECHO_SHOVEL = ITEMS.register("echo_shovel", EchoShovelItem::new);
    public static final DeferredItem<Item> ECHO_PICKAXE = ITEMS.register("echo_pickaxe", EchoPickaxeItem::new);
    public static final DeferredItem<Item> ECHO_AXE = ITEMS.register("echo_axe", EchoAxeItem::new);
    public static final DeferredItem<Item> ECHO_HOE = ITEMS.register("echo_hoe", EchoHoeItem::new);
    // 金属板
    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PLATE = ITEMS.register("copper_plate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_PLATE = ITEMS.register("gold_plate", () -> new Item(new Item.Properties()));

    // 注册到总线
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}