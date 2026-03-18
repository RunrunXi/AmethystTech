package me.cyanhana.amethyst_tech.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class EchoBlock extends Block {
    public static final MapCodec<EchoBlock> CODEC = simpleCodec(EchoBlock::new);

    @Override
    public MapCodec<? extends EchoBlock> codec() {
        return CODEC;
    }

    public EchoBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult block, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos blockpos = block.getBlockPos();
            level.playSound(null, blockpos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
            level.playSound(null, blockpos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
        }
    }

}
