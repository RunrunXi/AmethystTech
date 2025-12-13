package me.cyanhana.amethyst_tech.recipes;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.recipes.cutting.CuttingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {
    private ModRecipeTypes() {}

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, AmethystTech.MODID);
    // 注册配方类型
    public static final RecipeType<CuttingRecipe> CUTTING = register("cutting");

    private static <T extends Recipe<?>> RecipeType<T> register(String id) {
        RecipeType<T> type = RecipeType.simple(ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, id));
        RECIPE_TYPES.register(id, () -> type);
        return type;
    }

    // 注册到总线
    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}
