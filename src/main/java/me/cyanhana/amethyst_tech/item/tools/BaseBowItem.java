package me.cyanhana.amethyst_tech.item.tools;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class BaseBowItem extends BowItem implements IModBow {
    public BaseBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            // 获取箭
            ItemStack itemstack = player.getProjectile(stack);
            if (!itemstack.isEmpty()) {
                // 拉弓时间
                int charge = (int) ((this.getUseDuration(stack, entityLiving) - timeLeft) * getDrawSpeedMulti(stack));
                // 当放箭事件被拦截时为-1
                charge = net.neoforged.neoforge.event.EventHooks.onArrowLoose(stack, level, player, charge, !itemstack.isEmpty());
                if (charge < 0) return;
                // 获取拉弓力量
                float f = getPowerForTime(charge);
                if (!((double)f < 0.1)) {
                    // 获取要射出去的箭
                    List<ItemStack> list = draw(stack, itemstack, player);
                    // 射击
                    if (level instanceof ServerLevel serverlevel && !list.isEmpty()) {
                        this.shoot(serverlevel, player, player.getUsedItemHand(), stack, list, f * 3.0F, 1.0F, f == 1.0F, null);
                        onShoot(stack);
                    }
                    // 播放音效
                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    // 统计信息计数
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public void onShoot(ItemStack stack) {

    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
        arrow.setBaseDamage(arrow.getBaseDamage() * getDamageMulti(weaponStack));
        return arrow;
    }

}
