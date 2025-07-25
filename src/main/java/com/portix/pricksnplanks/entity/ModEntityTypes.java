package com.portix.pricksnplanks.entity;

import com.portix.pricksnplanks.PricksnPlanks;
import com.portix.pricksnplanks.entity.custom.CactusBoatEntity;
import com.portix.pricksnplanks.entity.custom.CactusChestBoatEntity;
import com.portix.pricksnplanks.entity.custom.CactusTntEntity;
import com.portix.pricksnplanks.entity.custom.ThrowableCactusEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<ThrowableCactusEntity> THROWABLE_CACTUS_ENTITY = register(
            Identifier.of(PricksnPlanks.MOD_ID, "throwable_cactus"),
            EntityType.Builder.<ThrowableCactusEntity>create(ThrowableCactusEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(0.3f, 0.3f) // size of the hitbox
                    //.maxTrackingRange(4)
                    //.trackingTickInterval(10)
    );

    public static final EntityType<BoatEntity> CACTUS_BOAT = register(
            Identifier.of(PricksnPlanks.MOD_ID, "cactus_boat"),
            EntityType.Builder.<BoatEntity>create(CactusBoatEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10)
    );

    public static final EntityType<ChestBoatEntity> CACTUS_CHEST_BOAT = register(
            Identifier.of(PricksnPlanks.MOD_ID, "cactus_chest_boat"),
            EntityType.Builder.<ChestBoatEntity>create(CactusChestBoatEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .maxTrackingRange(10)
    );

    public static final EntityType<CactusTntEntity> CACTUS_TNT = register(
            Identifier.of(PricksnPlanks.MOD_ID, "cactus_tnt"),
            EntityType.Builder.<CactusTntEntity>create(CactusTntEntity::new, SpawnGroup.MISC)
                    .dropsNothing()
                    .makeFireImmune()
                    .dimensions(0.98F, 0.98F)
                    .eyeHeight(0.15F)
                    .maxTrackingRange(10)
                    .trackingTickInterval(10)
    );

    private static <T extends Entity> EntityType<T> register(Identifier id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    private static RegistryKey<EntityType<?>> keyOf(Identifier id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
    }

    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    public static void registerModEntities() {
        PricksnPlanks.LOGGER.info("Registering Mod Entities for " + PricksnPlanks.MOD_ID);
    }
}
