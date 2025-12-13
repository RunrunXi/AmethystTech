package me.cyanhana.amethyst_tech.recipes;

import me.cyanhana.amethyst_tech.AmethystTech;
import me.cyanhana.amethyst_tech.recipes.cutting.CuttingRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializers {
    private ModRecipeSerializers() {}

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, AmethystTech.MODID);

    static {
        register("cutting", CuttingRecipeSerializer.INSTANCE);
    }

    private static void register(String id, RecipeSerializer<?> serializer) {
        RECIPE_SERIALIZERS.register(id, () -> serializer);
    }

    // 注册到总线
    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
