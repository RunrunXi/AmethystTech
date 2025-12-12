package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    // 构造函数-方块掉落物
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    // 生成 json文件
    @Override
    protected void generate() {
        // 掉落自身
        dropSelf(ModBlocks.ECHO_BLOCK.get());
    }

    // 获取所有模组已经注册的方块
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
