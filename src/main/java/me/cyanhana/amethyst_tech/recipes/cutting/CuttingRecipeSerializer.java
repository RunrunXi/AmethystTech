package me.cyanhana.amethyst_tech.recipes.cutting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class CuttingRecipeSerializer implements RecipeSerializer<CuttingRecipe> {
    public static final CuttingRecipeSerializer INSTANCE = new CuttingRecipeSerializer();

    public CuttingRecipeSerializer() {}

    private static final MapCodec<CuttingRecipe> CODEC = RecordCodecBuilder.<CuttingRecipe>mapCodec(
            builder -> builder.group(
                            Codec.STRING.optionalFieldOf("group", "").forGetter(cuttingRecipe -> cuttingRecipe.group),
                            CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(cuttingRecipe -> cuttingRecipe.category),
                            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(cuttingRecipe -> cuttingRecipe.result),
                            Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                    .flatXmap(
                                            r -> {
                                                // Neo skip the empty check and immediately create the array.
                                                Ingredient[] aingredient = r.toArray(Ingredient[]::new);
                                                if (aingredient.length == 0) {
                                                    return DataResult.error(() -> "No ingredients for cutting recipe");
                                                } else {
                                                    return aingredient.length > CuttingRecipe.maxHeight * CuttingRecipe.maxWidth
                                                            ? DataResult.error(() -> "Too many ingredients for cutting recipe. The maximum is: %s"
                                                            .formatted(CuttingRecipe.maxHeight * CuttingRecipe.maxWidth))
                                                            : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                                                }
                                            },
                                            DataResult::success
                                    )
                                    .forGetter(cuttingRecipe -> cuttingRecipe.ingredients)
                    )
                    .apply(builder, CuttingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, CuttingRecipe> STREAM_CODEC = StreamCodec.of(
            CuttingRecipeSerializer::toNetwork, CuttingRecipeSerializer::fromNetwork
    );

    private static void toNetwork(RegistryFriendlyByteBuf buffer, CuttingRecipe recipe) {
        buffer.writeUtf(recipe.group);
        buffer.writeEnum(recipe.category);
        buffer.writeVarInt(recipe.ingredients.size());

        for (Ingredient ingredient : recipe.ingredients) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
        }

        ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
    }
    private static CuttingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        String s = buffer.readUtf();
        CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
        int i = buffer.readVarInt();
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
        nonnulllist.replaceAll(p_319735_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
        ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
        return new CuttingRecipe(s, craftingbookcategory, itemstack, nonnulllist);
    }

    @Override
    public MapCodec<CuttingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CuttingRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
