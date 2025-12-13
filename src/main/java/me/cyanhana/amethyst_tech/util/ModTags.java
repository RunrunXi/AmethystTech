package me.cyanhana.amethyst_tech.util;

import me.cyanhana.amethyst_tech.AmethystTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public class Items {
        // 紫晶科技
        // 切割刀
        public static final TagKey<Item> CUTTING_KNIVES = modTag("cutting_knives");
        // 通用标签
        public static final TagKey<Item> PLATES = commonTag("plates");
        public static final TagKey<Item> IRON_PLATES = commonTag("plates/iron");
        public static final TagKey<Item> GOLD_PLATES = commonTag("plates/gold");
        public static final TagKey<Item> COPPER_PLATES = commonTag("plates/copper");

        private static TagKey<Item> modTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, name));
        }
        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
    public class Blocks {


        private static TagKey<Block> createTagKey(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, name));
        }
    }
}
