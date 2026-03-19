package me.cyanhana.amethyst_tech.item.tools.amethystTools;

import me.cyanhana.amethyst_tech.block.BuddingEchoBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

public class AmethystCuttingKnifeItem extends Item {
    static final double ATTACK_DAMAGE = 3.0;
    static final double ATTACK_SPEED = -2.0;

    public AmethystCuttingKnifeItem() {
        super(new Item.Properties()
                .durability(64)
                .stacksTo(1)
                .attributes(ItemAttributeModifiers.builder().
                        add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, ATTACK_DAMAGE, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, ATTACK_SPEED, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .build()
        ));
    }
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("gui.amethyst_tech.tooltip.cutting_knife_tooltip").withStyle(ChatFormatting.DARK_GRAY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null || !player.mayBuild())
            return super.useOn(context);

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(level, pos, level.getBlockState(pos), player);
        NeoForge.EVENT_BUS.post(event);
        if (event.isCanceled())
            return super.useOn(context);

        // 获取方块
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        Block block = state.getBlock();
        ItemStack itemInHand = context.getItemInHand();
        // 判断是否是母岩
        if (block instanceof BuddingEchoBlock || block instanceof BuddingAmethystBlock) {
            // 如果是生存模式才消耗耐久
            if (player.isShiftKeyDown()) {
                if (!player.isCreative()) {
                    player.getInventory().placeItemBackInInventory(new ItemStack(block));
                    context.getItemInHand().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                level.destroyBlock(pos, true);
                return InteractionResult.SUCCESS;
            }
        }

        return super.useOn(context);
    }


}
