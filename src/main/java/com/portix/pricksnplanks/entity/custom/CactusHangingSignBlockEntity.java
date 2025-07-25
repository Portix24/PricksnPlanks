package com.portix.pricksnplanks.entity.custom;

import com.portix.pricksnplanks.entity.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.HangingSignBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;

public class CactusHangingSignBlockEntity extends SignBlockEntity {
    public CactusHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CACTUS_HANGING_SIGN_BLOCK_ENTITY, pos, state);
    }
}
