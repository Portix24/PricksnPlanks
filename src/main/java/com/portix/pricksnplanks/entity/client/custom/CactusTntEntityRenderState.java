package com.portix.pricksnplanks.entity.client.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CactusTntEntityRenderState extends EntityRenderState {
    public float fuse;
    @Nullable
    public BlockState blockState;
}
