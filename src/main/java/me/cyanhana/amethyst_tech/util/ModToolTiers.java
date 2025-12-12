package me.cyanhana.amethyst_tech.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    // 紫晶工具
    // 工具特性: 铁的属性, 铜的耐久, 金的附魔值, 钻石的伤害
    public static final Tier AMETHYST = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            190, 6F, 3F, 22,
            () -> Ingredient.of(Blocks.AMETHYST_BLOCK));
    // 回响工具
    // 工具特性: 钻石的属性, 钻石和铁之间的耐久, 下界合金的伤害
    public static final Tier ECHO = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            905, 8F, 4F, 10,
            () -> Ingredient.of(Items.ECHO_SHARD));
}
