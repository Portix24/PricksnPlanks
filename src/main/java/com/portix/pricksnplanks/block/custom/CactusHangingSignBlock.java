package com.portix.pricksnplanks.block.custom;

import com.portix.pricksnplanks.entity.custom.CactusHangingSignBlockEntity;
import com.portix.pricksnplanks.entity.custom.CactusSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CactusHangingSignBlock extends HangingSignBlock {
    public CactusHangingSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CactusHangingSignBlockEntity(pos, state);
    }
}
