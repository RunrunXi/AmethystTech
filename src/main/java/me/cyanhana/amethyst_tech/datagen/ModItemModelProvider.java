package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.item.ModItems;
import net.minecraft.data.PackOutput;
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
        basicItem(ModItems.AMETHYST_SWORD.get());
        basicItem(ModItems.AMETHYST_SHOVEL.get());
        basicItem(ModItems.AMETHYST_PICKAXE.get());
        basicItem(ModItems.AMETHYST_AXE.get());
        basicItem(ModItems.AMETHYST_HOE.get());
        // 回响工具
        basicItem(ModItems.ECHO_SWORD.get());
        basicItem(ModItems.ECHO_SHOVEL.get());
        basicItem(ModItems.ECHO_PICKAXE.get());
        basicItem(ModItems.ECHO_AXE.get());
        basicItem(ModItems.ECHO_HOE.get());
        // 切割刀
        basicItem(ModItems.AMETHYST_CUTTING_KNIFE.get());
    }
}
