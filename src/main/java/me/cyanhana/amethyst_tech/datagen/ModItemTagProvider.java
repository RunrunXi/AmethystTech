package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.block.ModBlocks;
import me.cyanhana.amethyst_tech.item.ModItems;
import me.cyanhana.amethyst_tech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AmethystTech.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS)
                .add(ModItems.AMETHYST_SWORD.get())
                .add(ModItems.ECHO_SWORD.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.AMETHYST_SHOVEL.get())
                .add(ModItems.ECHO_SHOVEL.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.AMETHYST_PICKAXE.get())
                .add(ModItems.ECHO_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(ModItems.AMETHYST_AXE.get())
                .add(ModItems.ECHO_AXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.AMETHYST_HOE.get())
                .add(ModItems.ECHO_HOE.get());
        // 弓
        tag(Tags.Items.TOOLS_BOW)
                .add(ModItems.AMETHYST_BOW.get())
                .add(ModItems.ECHO_BOW.get());
        tag(Tags.Items.RANGED_WEAPON_TOOLS)
                .add(ModItems.AMETHYST_BOW.get())
                .add(ModItems.ECHO_BOW.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.AMETHYST_BOW.get())
                .add(ModItems.ECHO_BOW.get())
                .add(ModItems.AMETHYST_CUTTING_KNIFE.get());
        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.AMETHYST_BOW.get())
                .add(ModItems.ECHO_BOW.get());
        // 切割刀
        tag(ModTags.Items.CUTTING_KNIVES)
                .add(ModItems.AMETHYST_CUTTING_KNIFE.get());
        // 金属板
        tag(ModTags.Items.PLATES)
                .add(ModItems.IRON_PLATE.get())
                .add(ModItems.GOLD_PLATE.get())
                .add(ModItems.COPPER_PLATE.get());
        tag(ModTags.Items.IRON_PLATE).add(ModItems.IRON_PLATE.get());
        tag(ModTags.Items.GOLD_PLATE).add(ModItems.GOLD_PLATE.get());
        tag(ModTags.Items.COPPER_PLATE).add(ModItems.COPPER_PLATE.get());
        // 线
        tag(ModTags.Items.WIRES)
                .add(ModItems.COPPER_WIRE.get())
                .add(ModItems.GOLD_WIRE.get());
        tag(ModTags.Items.COPPER_WIRE)
                .add(ModItems.COPPER_WIRE.get());
        tag(ModTags.Items.GOLD_WIRE)
                .add(ModItems.GOLD_WIRE.get());
        // 线圈
        tag(ModTags.Items.COILS)
                .add(ModItems.LV_RESONANT_COIL.get())
                .add(ModItems.MV_RESONANT_COIL.get());
        tag(ModTags.Items.LV_COIL)
                .add(ModItems.LV_RESONANT_COIL.get());
        tag(ModTags.Items.MV_COIL)
                .add(ModItems.MV_RESONANT_COIL.get());

        tag(ItemTags.CLUSTER_MAX_HARVESTABLES)
                .add(ModItems.AMETHYST_PICKAXE.get())
                .add(ModItems.ECHO_PICKAXE.get());
        // 母岩
        tag(Tags.Items.BUDDING_BLOCKS)
                .add(ModBlocks.BUDDING_ECHO.asItem());
        tag(Tags.Items.BUDS)
                .add(ModBlocks.SMALL_ECHO_BUD.asItem())
                .add(ModBlocks.MEDIUM_ECHO_BUD.asItem())
                .add(ModBlocks.LARGE_ECHO_BUD.asItem());
    }
}
