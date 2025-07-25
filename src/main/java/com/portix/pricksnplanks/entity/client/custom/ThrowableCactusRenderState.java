package com.portix.pricksnplanks.entity.client.custom;

import com.portix.pricksnplanks.entity.custom.ThrowableCactusEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ThrowableCactusRenderState extends EntityRenderState {
    public ItemStack stack;
    public float tickDelta;
    public ThrowableCactusEntity entity;

    public ThrowableCactusRenderState() {
    }
}
