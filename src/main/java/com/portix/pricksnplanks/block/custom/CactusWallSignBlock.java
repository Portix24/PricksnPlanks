package com.portix.pricksnplanks.block.custom;

import com.portix.pricksnplanks.entity.custom.CactusSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CactusWallSignBlock extends WallSignBlock {
    public CactusWallSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CactusSignBlockEntity(pos, state);
    }
}
