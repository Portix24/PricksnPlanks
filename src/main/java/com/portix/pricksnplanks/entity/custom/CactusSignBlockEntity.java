package com.portix.pricksnplanks.entity.custom;

import com.portix.pricksnplanks.entity.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

public class CactusSignBlockEntity extends SignBlockEntity {
    private static final int MAX_TEXT_WIDTH = 60;
    private static final int TEXT_LINE_HEIGHT = 9;

    public CactusSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CACTUS_SIGN_BLOCK_ENTITY, pos, state);
    }

    @Override
    public int getTextLineHeight() {
        return TEXT_LINE_HEIGHT;
    }

    @Override
    public int getMaxTextWidth() {
        return MAX_TEXT_WIDTH;
    }

    @Override
    public SoundEvent getInteractionFailSound() {
        return SoundEvents.BLOCK_HANGING_SIGN_WAXED_INTERACT_FAIL;
    }
}
