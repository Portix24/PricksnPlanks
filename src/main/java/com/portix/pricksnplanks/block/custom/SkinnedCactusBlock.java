package com.portix.pricksnplanks.block.custom;

import com.mojang.serialization.MapCodec;
import com.portix.pricksnplanks.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class SkinnedCactusBlock extends Block {
    public static final MapCodec<SkinnedCactusBlock> CODEC = createCodec(SkinnedCactusBlock::new);
    public static final IntProperty AGE = Properties.AGE_15;
    private static final VoxelShape OUTLINE_SHAPE = Block.createColumnShape(14.0, 0.0, 16.0);
    private static final VoxelShape COLLISION_SHAPE = Block.createColumnShape(14.0, 0.0, 15.0);

    @Override
    public MapCodec<SkinnedCactusBlock> getCodec() {
        return CODEC;
    }

    public SkinnedCactusBlock(Settings settings) {
        super(settings);
        //this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    /*
    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos blockPos = pos.up();
        if (world.isAir(blockPos)) {
            int i = 1;
            int j = (Integer)state.get(AGE);

            while (world.getBlockState(pos.down(i)).isOf(this)) {
                if (++i == 3 && j == 15) {
                    return;
                }
            }

            if (j == 8 && this.canPlaceAt(this.getDefaultState(), world, pos.up())) {
                double d = i >= 3 ? 0.25 : 0.1;
                if (random.nextDouble() <= d) {
                    world.setBlockState(blockPos, Blocks.CACTUS_FLOWER.getDefaultState());
                }
            } else if (j == 15 && i < 3) {
                world.setBlockState(blockPos, this.getDefaultState());
                BlockState blockState = state.with(AGE, 0);
                world.setBlockState(pos, blockState, Block.SKIP_REDRAW_AND_BLOCK_ENTITY_REPLACED_CALLBACK);
                world.updateNeighbor(blockState, blockPos, this, null, false);
            }

            if (j < 15) {
                world.setBlockState(pos, state.with(AGE, j + 1), Block.SKIP_REDRAW_AND_BLOCK_ENTITY_REPLACED_CALLBACK);
            }
        }
    }
     */

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        if (!state.canPlaceAt(world, pos)) {
            tickView.scheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            BlockState blockState = world.getBlockState(pos.offset(direction));
            if (blockState.isSolid() || world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA)) {
                return false;
            }
        }

        BlockState blockState2 = world.getBlockState(pos.down());
        return (blockState2.isIn(ModTags.Blocks.CACTUSES) || blockState2.isIn(BlockTags.SAND)) && !world.getBlockState(pos.up()).isLiquid();
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return true;
    }
}
