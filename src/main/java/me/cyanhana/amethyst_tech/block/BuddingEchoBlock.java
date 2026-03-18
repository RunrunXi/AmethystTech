package me.cyanhana.amethyst_tech.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingEchoBlock extends EchoBlock {
    public static final MapCodec<BuddingEchoBlock> CODEC = simpleCodec(BuddingEchoBlock::new);
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<BuddingEchoBlock> codec() {
        return CODEC;
    }

    public BuddingEchoBlock(Properties properties) {
        super(properties);
    }

    // 接收到随机刻时
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 有 20% 的概率产生生长
        if (random.nextInt(GROWTH_CHANCE) == 0) {
            // 获取周围方向上的方块
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            // 检查是否能生长
            if (canClusterGrowAtState(blockstate)) {
                block = ModBlocks.SMALL_ECHO_BUD.get();
            } else if (blockstate.is(ModBlocks.SMALL_ECHO_BUD.get()) && blockstate.getValue(EchoClusterBlock.FACING) == direction) {
                block = ModBlocks.MEDIUM_ECHO_BUD.get();
            } else if (blockstate.is(ModBlocks.MEDIUM_ECHO_BUD.get()) && blockstate.getValue(EchoClusterBlock.FACING) == direction) {
                block = ModBlocks.LARGE_ECHO_BUD.get();
            } else if (blockstate.is(ModBlocks.LARGE_ECHO_BUD.get()) && blockstate.getValue(EchoClusterBlock.FACING) == direction) {
                block = ModBlocks.ECHO_CLUSTER.get();
            }

            if (block != null) {
                BlockState newBlockstate = block.defaultBlockState()
                        .setValue(EchoClusterBlock.FACING, direction)
                        .setValue(EchoClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
                level.setBlockAndUpdate(blockpos, newBlockstate);
            }
        }
    }

    // 判断晶簇能否生长
    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}
