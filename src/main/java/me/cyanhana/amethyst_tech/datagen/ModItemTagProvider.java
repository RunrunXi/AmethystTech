package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.AmethystTech;
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
        // 切割刀
        tag(ModTags.Items.CUTTING_KNIVES)
                .add(ModItems.AMETHYST_CUTTING_KNIFE.get());
    }
}
