package com.portix.pricksnplanks.entity.custom;

import com.portix.pricksnplanks.block.ModBlocks;
import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.item.ModItems;
import com.portix.pricksnplanks.util.CactusTntExplosionBehavior;
import com.portix.pricksnplanks.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CactusTntEntity extends Entity implements Ownable {
    public static ExplosionBehavior CUSTOM_EXPLOSION_BEHAVIOR = new CactusTntExplosionBehavior(
            false, true, Optional.empty(), List.of(ModBlocks.CACTUS_TNT, Blocks.TNT) //Optional.empty(),
    );

    private static final TrackedData<Integer> FUSE = DataTracker.registerData(CactusTntEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<BlockState> BLOCK_STATE = DataTracker.registerData(CactusTntEntity.class, TrackedDataHandlerRegistry.BLOCK_STATE);
    private static final BlockState DEFAULT_BLOCK_STATE = ModBlocks.CACTUS_TNT.getDefaultState();
    private static final ExplosionBehavior TELEPORTED_EXPLOSION_BEHAVIOR = new ExplosionBehavior() {
        @Override
        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
            return !state.isOf(Blocks.NETHER_PORTAL) && super.canDestroyBlock(explosion, world, pos, state, power);
        }

        @Override
        public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
            return blockState.isOf(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
        }
    };

    @Nullable
    private LazyEntityReference<LivingEntity> causingEntity;
    private boolean teleported;
    private float explosionPower = 4.0F;

    public CactusTntEntity(EntityType<? extends CactusTntEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    /*
    public CactusTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(world, x, y, z, igniter);
    }
     */

    public CactusTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntityTypes.CACTUS_TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setVelocity(-Math.sin(d) * 0.02, 0.2F, -Math.cos(d) * 0.02);
        this.setFuse(80);
        this.lastX = x;
        this.lastY = y;
        this.lastZ = z;
        this.causingEntity = igniter != null ? new LazyEntityReference<>(igniter) : null;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(FUSE, 80);
        builder.add(BLOCK_STATE, DEFAULT_BLOCK_STATE);
    }

    @Override
    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    protected double getGravity() {
        return 0.04;
    }

    @Override
    public void tick() {
        this.tickPortalTeleportation();
        this.applyGravity();
        this.move(MovementType.SELF, this.getVelocity());
        this.tickBlockCollision();
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticleClient(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {
        if (this.getWorld() instanceof ServerWorld serverWorld && serverWorld.getGameRules().getBoolean(GameRules.TNT_EXPLODES)) {
            shootOutCactusBalls(serverWorld, this.getPos(), (PlayerEntity) this.getOwner());

            this.getWorld()
                    .createExplosion(
                            this,
                            Explosion.createDamageSource(this.getWorld(), this),
                            CUSTOM_EXPLOSION_BEHAVIOR,  //this.teleported ? TELEPORTED_EXPLOSION_BEHAVIOR : null,
                            this.getX(),
                            this.getBodyY(0.0625),
                            this.getZ(),
                            this.explosionPower,
                            false,
                            World.ExplosionSourceType.TNT
                    );
        }
    }

    private void shootOutCactusBalls(World world, Vec3d centerPos, PlayerEntity owner) {
        //List<Integer> angles = List.of(0, 45, 90, 135, 180, 225, 270, 315);
        double distance = 0.6;

        for (int angle = 0; angle <= 360; angle += 30) {
            angle += world.random.nextBetween(-8, 8);
            Vec3d pos = centerPos.add(Math.sin(Math.toRadians(angle)) * distance, 0.4, Math.cos(Math.toRadians(angle)) * distance);

            ThrowableCactusEntity cactusBall;
            if (owner != null) {
                cactusBall = new ThrowableCactusEntity(world, owner);
                cactusBall.setPosition(pos);
            } else {
                cactusBall = new ThrowableCactusEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.THROWABLE_CACTUS));
            }

            cactusBall.setUnpickupable(true);
            world.spawnEntity(cactusBall);
        }

        /*
        angles.forEach(angle -> {
            angle += world.random.nextBetween(-10, 10);
            Vec3d pos = centerPos.add(Math.sin(Math.toRadians(angle)) * distance, 0.4, Math.cos(Math.toRadians(angle)) * distance);
            ThrowableCactusEntity cactusBall;
            if (owner != null) {
                cactusBall = new ThrowableCactusEntity(world, owner);
                cactusBall.setPosition(pos);
            } else {
                cactusBall = new ThrowableCactusEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.THROWABLE_CACTUS));
            }

            cactusBall.setUnpickupable(true);
            //cactusBall.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

            world.spawnEntity(cactusBall);
        });
         */
    }

    @Override
    protected void writeCustomData(WriteView view) {
        view.putShort("fuse", (short)this.getFuse());
        view.put("block_state", BlockState.CODEC, this.getBlockState());
        if (this.explosionPower != 4.0F) {
            view.putFloat("explosion_power", this.explosionPower);
        }

        LazyEntityReference.writeData(this.causingEntity, view, "owner");
    }

    @Override
    protected void readCustomData(ReadView view) {
        this.setFuse(view.getShort("fuse", (short)80));
        this.setBlockState((BlockState)view.read("block_state", BlockState.CODEC).orElse(DEFAULT_BLOCK_STATE));
        this.explosionPower = MathHelper.clamp(view.getFloat("explosion_power", 4.0F), 0.0F, 128.0F);
        this.causingEntity = LazyEntityReference.fromData(view, "owner");
    }

    @Nullable
    public LivingEntity getOwner() {
        return (LivingEntity)LazyEntityReference.resolve(this.causingEntity, this.getWorld(), LivingEntity.class);
    }

    @Override
    public void copyFrom(Entity original) {
        super.copyFrom(original);
        if (original instanceof CactusTntEntity cactusTntEntity) {
            this.causingEntity = cactusTntEntity.causingEntity;
        }
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return this.dataTracker.get(FUSE);
    }

    public void setBlockState(BlockState state) {
        this.dataTracker.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return this.dataTracker.get(BLOCK_STATE);
    }

    private void setTeleported(boolean teleported) {
        this.teleported = teleported;
    }

    @Nullable
    @Override
    public Entity teleportTo(TeleportTarget teleportTarget) {
        Entity entity = super.teleportTo(teleportTarget);
        if (entity instanceof CactusTntEntity cactusTntEntity) {
            cactusTntEntity.setTeleported(true);
        }

        return entity;
    }

    @Override
    public final boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }
}
