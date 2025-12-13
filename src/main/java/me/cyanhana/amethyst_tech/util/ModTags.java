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
        public static final TagKey<Item> CUTTING_KNIVES = createTagKey("cutting_knives");

        private static TagKey<Item> createTagKey(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, name));
        }
    }
    public class Blocks {


        private static TagKey<Block> createTagKey(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, name));
        }
    }
}
