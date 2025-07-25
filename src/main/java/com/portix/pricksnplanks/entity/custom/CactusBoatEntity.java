package com.portix.pricksnplanks.entity.custom;

import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class CactusBoatEntity extends BoatEntity {
    public CactusBoatEntity(EntityType<BoatEntity> entityType, World world, Supplier<Item> supplier) {
        super(entityType, world, supplier);
    }

    public CactusBoatEntity(EntityType<BoatEntity> entityType, World world) {
        this(entityType, world, () -> ModItems.CACTUS_BOAT);
    }
}
