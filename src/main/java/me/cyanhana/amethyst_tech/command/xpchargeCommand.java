package me.cyanhana.amethyst_tech.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.cyanhana.amethyst_tech.item.tools.echoTools.EchoItem;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

import static net.minecraft.commands.Commands.*;

public class xpchargeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        var xpcharge = dispatcher.register(literal("xpcharge").requires(s -> s.hasPermission(2))
                .then(argument("target", EntityArgument.entities())
                        .then(literal("set")
                                .then(argument("amount", IntegerArgumentType.integer())
                                        .executes(ctx ->
                                                setXpCharge(
                                                        ctx.getSource(),
                                                        EntityArgument.getEntities(ctx, "target"),
                                                        IntegerArgumentType.getInteger(ctx, "amount")))))
                        .then(literal("add")
                                .then(argument("amount", IntegerArgumentType.integer())
                                        .executes(ctx ->
                                                addXpCharge(
                                                        ctx.getSource(),
                                                        EntityArgument.getEntities(ctx, "target"),
                                                        IntegerArgumentType.getInteger(ctx, "amount")))))));
    }

    public static int setXpCharge(CommandSourceStack source, Collection<? extends Entity> targets, int amount) {
        if (source.getLevel() instanceof ServerLevel) {
            // 获取实体
            for (Entity target : targets) {
                if (target instanceof LivingEntity entity) {
                    // 获取主手
                    ItemStack mainHand = entity.getItemInHand(InteractionHand.MAIN_HAND);
                    if (mainHand.getItem() instanceof EchoItem echoItem) {
                        echoItem.setXpCharge(mainHand, amount);
                        return amount;
                    }
                    // 获取副手
                    ItemStack offHand = entity.getItemInHand(InteractionHand.OFF_HAND);
                    if (offHand.getItem() instanceof EchoItem echoItem) {
                        echoItem.setXpCharge(offHand, amount);
                        return amount;
                    }
                }
            }
        }
        return 0;
    }

    public static int addXpCharge(CommandSourceStack source, Collection<? extends Entity> targets, int amount) {
        if (source.getLevel() instanceof ServerLevel) {
            // 获取实体
            for (Entity target : targets) {
                if (target instanceof LivingEntity entity) {
                    // 获取主手
                    ItemStack mainHand = entity.getItemInHand(InteractionHand.MAIN_HAND);
                    if (mainHand.getItem() instanceof EchoItem echoItem) {
                        int charge = echoItem.getXpCharge(mainHand);
                        echoItem.setXpCharge(mainHand, charge + amount);
                        return amount;
                    }
                    // 获取副手
                    ItemStack offHand = entity.getItemInHand(InteractionHand.OFF_HAND);
                    if (offHand.getItem() instanceof EchoItem echoItem) {
                        int charge = echoItem.getXpCharge(offHand);
                        echoItem.setXpCharge(offHand, charge + amount);
                        return amount;
                    }
                }
            }
        }
        return 0;
    }
}
