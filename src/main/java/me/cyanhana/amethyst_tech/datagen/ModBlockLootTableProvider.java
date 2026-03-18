package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.block.ModBlocks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    // 构造函数-方块掉落物
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    // 生成 json文件
    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        // 掉落自身
        dropSelf(ModBlocks.ECHO_BLOCK.get());
        // 晶簇 精准采集掉落
        dropWhenSilkTouch(ModBlocks.SMALL_ECHO_BUD.get());
        dropWhenSilkTouch(ModBlocks.MEDIUM_ECHO_BUD.get());
        dropWhenSilkTouch(ModBlocks.LARGE_ECHO_BUD.get());
        // 母岩 不掉落
        add(ModBlocks.BUDDING_ECHO.get(), noDrop());
        // 簇 特殊掉落
        this.add(ModBlocks.ECHO_CLUSTER.get(),
                block -> this.createSilkTouchDispatchTable(
                        block,
                        LootItem.lootTableItem(Items.ECHO_SHARD)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .otherwise(
                                        this.applyExplosionDecay(
                                                block,
                                                LootItem.lootTableItem(Items.ECHO_SHARD)
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                        )
                                )
                )
        );
    }

    // 获取所有模组已经注册的方块
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
