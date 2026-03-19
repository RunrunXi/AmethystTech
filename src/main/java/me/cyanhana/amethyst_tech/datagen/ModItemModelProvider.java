package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AmethystTech.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // 锻造模版
        basicItem(ModItems.ECHO_UPGRADE_SMITHING_TEMPLATE.get());
        // 紫晶工具
        handheldItem(ModItems.AMETHYST_SWORD.get());
        handheldItem(ModItems.AMETHYST_SHOVEL.get());
        handheldItem(ModItems.AMETHYST_PICKAXE.get());
        handheldItem(ModItems.AMETHYST_AXE.get());
        handheldItem(ModItems.AMETHYST_HOE.get());
        // 回响工具
        handheldItem(ModItems.ECHO_SWORD.get());
        handheldItem(ModItems.ECHO_SHOVEL.get());
        handheldItem(ModItems.ECHO_PICKAXE.get());
        handheldItem(ModItems.ECHO_AXE.get());
        handheldItem(ModItems.ECHO_HOE.get());
        // 切割刀
        handheldItem(ModItems.AMETHYST_CUTTING_KNIFE.get());
        // 金属板
        basicItem(ModItems.IRON_PLATE.get());
        basicItem(ModItems.COPPER_PLATE.get());
        basicItem(ModItems.GOLD_PLATE.get());
        // 线
        basicItem(ModItems.COPPER_WIRE.get());
    }

    private ResourceLocation blockItem(String name) {
        return ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "block/" + name);
    }
}
