package me.cyanhana.amethyst_tech;

import com.mojang.logging.LogUtils;
import me.cyanhana.amethyst_tech.block.ModBlocks;
import me.cyanhana.amethyst_tech.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// 此处的值应与META-INF/neoforge.mods.toml文件中的某个条目相匹配
@Mod(AmethystTech.MODID)
public class AmethystTech {
    // 定义模组ID，供所有内容引用
    public static final String MODID = "amethyst_tech";
    // 直接引用一个slf4j日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    // 创建一个新的食物物品，ID为"amethyst_tech:example_id"，营养值为1，饱腹值为2
//    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // 模组类的构造函数是在模组加载时运行的第一个代码。
    // FML会自动识别一些参数类型，如IEventBus或ModContainer，并传入相应的实例。
    public AmethystTech(IEventBus modEventBus, ModContainer modContainer) {
        // 注册commonSetup方法以进行模组加载设置
        modEventBus.addListener(this::commonSetup);

        // 注册以接收我们感兴趣的服务器及其他游戏事件。
        // 请注意，仅当我们希望本类（Amethyst_tech）能直接响应事件时，才需要添加此代码。
        // 如果此类中没有像下文onServerStarting()这样使用@SubscribeEvent注解的函数，请勿添加此行。
        NeoForge.EVENT_BUS.register(this);

        // 注册创造模式标签页
        ModCreativeModeTabs.register(modEventBus);
        // 注册物品
        ModItems.register(modEventBus);
        // 注册方块
        ModBlocks.register(modEventBus);

        // 注册模组的ModConfigSpec以让FML自动创建和加载配置文件
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /**
     * 公共模组设置，在模组加载的公共阶段执行。
     * @param event FML公共设置事件，提供模组初始化的通用环境。
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
//        // 公共设置代码
//        LOGGER.info("[公共设置] 初始化开始");
//
//        // 根据配置决定是否记录泥土方块注册信息
//        if (Config.logDirtBlock) {
//            LOGGER.info("泥土方块注册键 >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
//        }
//
//        // 输出魔法数介绍及数值
//        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
//
//        // 遍历配置中的物品列表并输出
//        Config.items.forEach((item) -> LOGGER.info("配置物品 >> {}", item.toString()));
    }

    /**
     * 服务器启动事件处理。
     * 使用@SubscribeEvent注解让事件总线自动发现并调用此方法。
     * @param event 服务器启动事件。
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // 服务器启动时的操作
        LOGGER.info("[服务器启动] 模组已就绪");
    }

    /**
     * 客户端事件订阅者类。
     * 使用@EventBusSubscriber注解自动注册本类中所有带@SubscribeEvent的静态方法。
     * modid: 指定所属模组ID
     * bus: 指定事件总线类型（MOD总线）
     * value: 指定仅客户端环境下生效
     */
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        /**
         * 客户端设置事件处理。
         * @param event FML客户端设置事件。
         */
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // 客户端专用设置代码
            LOGGER.info("[客户端设置] 初始化开始");
            // 获取并输出当前Minecraft玩家名称
            LOGGER.info("玩家名称 >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
