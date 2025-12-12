package me.cyanhana.amethyst_tech.block;
import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/* 如何注册一个方块:
* 1. 创建一个DeferredBlock<Block>
* 2. 添加到创造模式物品栏
* 3. 填写生成器: 方块掉落物, 方块状态, 方块标签
* 4. 添加材质文件
* 5. 添加语言文件
* 可能需要填写配方生成器
*  */
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AmethystTech.MODID);
    // 对于每一个自定义的方块,如果都是默认的,就可以直接套用模板,当然一些重要的,需要自定义的方块,就得自己写一个模板出来.
//    public static final DeferredBlock<Block> SAMPLE_STONELIKE_BLOCK = registerBlock("zombie_iron_block",
//            CoralBlock_sample::new);
    // 回响块
    public static final DeferredBlock<Block> ECHO_BLOCK = registerBlock("echo_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.5F)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops()
            ));

    // 注册方块(自动注册方块物品)
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    // 注册方块物品
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties()));
    }

    // 注册到总线
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
