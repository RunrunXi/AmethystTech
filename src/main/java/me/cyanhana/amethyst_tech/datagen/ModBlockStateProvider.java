package me.cyanhana.amethyst_tech.datagen;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AmethystTech.MODID, exFileHelper);
    }
    // 生成 json代码
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ECHO_BLOCK);
        blockWithItem(ModBlocks.BUDDING_ECHO);
        budBlock(ModBlocks.ECHO_CLUSTER);
        budBlock(ModBlocks.SMALL_ECHO_BUD);
        budBlock(ModBlocks.MEDIUM_ECHO_BUD);
        budBlock(ModBlocks.LARGE_ECHO_BUD);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    // 紫水晶簇
    private void budBlock(DeferredBlock<?> deferredBlock) {
        Block block = deferredBlock.get();
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "block/" + name);
        ModelFile model = models().cross(name, texture).renderType("cutout");
        directionalBlock(block, model);
        itemModels().withExistingParent(name, mcLoc("item/generated")).texture("layer0", texture);
    }

}
