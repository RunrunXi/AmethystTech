package me.cyanhana.amethyst_tech.util;

import com.mojang.serialization.Codec;
import me.cyanhana.amethyst_tech.AmethystTech;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DR = DeferredRegister
            .create(Registries.DATA_COMPONENT_TYPE, AmethystTech.MODID);

    public static final DataComponentType<Integer> XP_CHARGE = registerDataComponent("xp_charge",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT));

    private static <T> DataComponentType<T> registerDataComponent(String name, Consumer<DataComponentType.Builder<T>> customizer) {
        var builder = DataComponentType.<T>builder();
        customizer.accept(builder);
        var componentType = builder.build();
        DR.register(name, () -> componentType);
        return componentType;
    }

    public static void register(IEventBus eventBus) {
        DR.register(eventBus);
    }
}
