package me.cyanhana.amethyst_tech.util;

import me.cyanhana.amethyst_tech.AmethystTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        // 紫晶科技
        // 切割刀
        public static final TagKey<Item> CUTTING_KNIVES = modTag("cutting_knives");
        // 通用标签
        public static final TagKey<Item> PLATES = commonTag("plates");
        public static final TagKey<Item> WIRES = commonTag("wires");
        public static final TagKey<Item> COILS = modTag("coils");
        // 板
        public static final TagKey<Item> IRON_PLATE = commonTag("plates/iron");
        public static final TagKey<Item> GOLD_PLATE = commonTag("plates/gold");
        public static final TagKey<Item> COPPER_PLATE = commonTag("plates/copper");
        // 线
        public static final TagKey<Item> COPPER_WIRE = commonTag("wires/copper");
        public static final TagKey<Item> GOLD_WIRE = commonTag("wires/gold");
        // 线圈
        public static final TagKey<Item> LV_COIL = modTag("coils/lv");
        public static final TagKey<Item> MV_COIL = modTag("coils/mv");

        private static TagKey<Item> modTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, name));
        }
        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
    public static class Blocks {

        private static TagKey<Block> modTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, name));
        }
        private static TagKey<Block> commonTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
