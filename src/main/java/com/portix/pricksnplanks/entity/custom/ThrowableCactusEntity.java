package com.portix.pricksnplanks.entity.custom;

import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ThrowableCactusEntity extends PersistentProjectileEntity {
    private LivingEntity attachedEntity;
    private Vec3d entityAttachOffset;
    private double spinAgeCounter;
    private int attachTimer;
    private int lifetime;
    private boolean canBePickedUp;

    public ThrowableCactusEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        initVariables();
    }

    public ThrowableCactusEntity(World world, LivingEntity owner) {
        super(ModEntityTypes.THROWABLE_CACTUS_ENTITY, owner, world, new ItemStack(ModItems.THROWABLE_CACTUS), null);
        initVariables();
    }

    public ThrowableCactusEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntityTypes.THROWABLE_CACTUS_ENTITY, x, y, z, world, stack, null);
        initVariables();
    }

    private void initVariables() {
        this.lifetime = 2400;   // 2 minutes
        this.attachTimer = 200;
        this.canBePickedUp = true;
        this.spinAgeCounter = this.getRandom().nextInt(15);
    }

    public boolean shouldSpin() {
        boolean ifEntityFits = false;
        if (attachedEntity != null) {
            ifEntityFits = !attachedEntity.isAlive() || attachedEntity.isDead() || attachedEntity.isSpectator();
        }
        return (this.attachedEntity == null || ifEntityFits) && !this.isInGround();
    }

    public double getSpinAgeCounter() {
        return spinAgeCounter;
    }

    public void increaseSpinAgeCounter() {
        // There is the option to floor this and add tickDelta for smoothness, but that's not needed for now
        spinAgeCounter += 1;
    }

    public void setUnpickupable(boolean unpickupable) {
        this.canBePickedUp = !unpickupable;
        this.pickupType = PickupPermission.CREATIVE_ONLY;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.THROWABLE_CACTUS);
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(ModItems.THROWABLE_CACTUS);
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        Entity target = hitResult.getEntity();

        if (target instanceof LivingEntity living) {
            this.playSound(this.getSound(), 0.8F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 0.075F, 0.35F + (this.random.nextFloat() * 0.3F));

            // Store who you're attached to
            this.attachedEntity = living;
            this.entityAttachOffset = hitResult.getPos().subtract(
                    this.attachedEntity.getPos()).add(
                            this.getVelocity().multiply(
                                    Math.max((living.getBoundingBox().getAverageSideLength() - 1.5) / 2, 0.2)
                            )
            );

            // Make the projectile stick — don't remove it immediately
            this.setNoGravity(true);
            this.setVelocity(Vec3d.ZERO);
            this.setNoClip(true);

            this.setPosition(hitResult.getPos());

            //this.setPosition(target.getX(), target.getY() + target.getHeight() / 2, target.getZ());

            // Optional: apply damage or status effect
            if (!target.getWorld().isClient) {
                target.damage((ServerWorld) target.getWorld(), (this.getOwner() != null ? this.getOwner() : this).getDamageSources().cactus(), 3.0f);
            }
        }
    }

    @Override
    public void tick() {
        if (attachedEntity != null && attachedEntity.isAlive() && !attachedEntity.isDead() && !attachedEntity.isSpectator()) {
            /*
            this.setPosition(
                    attachedEntity.getX(),
                    attachedEntity.getY() + attachedEntity.getHeight() / 2,
                    attachedEntity.getZ()
            );
             */

            this.setPosition(this.attachedEntity.getPos().add(this.entityAttachOffset));

            if (this.getWorld() instanceof ServerWorld serverWorld) {
                if (this.age % 15 == 0) {
                    this.attachedEntity.damage(serverWorld, (this.getOwner() != null ? this.getOwner() : this).getDamageSources().cactus(), 1.0f);
                }
            }

            attachTimer--;
            if (attachTimer <= 0) {
                //this.discard();
                this.attachedEntity = null;
            }
        } else {
            this.setNoGravity(false);
            this.setNoClip(false);
            // Not stuck, normal behavior
            //if (this.age > 100) this.discard();
            if (this.isInGround()) {
                lifetime--;
                if (lifetime <= 0) {
                    this.discard();
                }
            }
        }

        boolean ifEntityIsGone = false;
        if (attachedEntity != null) {
            ifEntityIsGone = !attachedEntity.isAlive() || attachedEntity.isDead() || attachedEntity.isSpectator();
        }
        if ((this.attachedEntity == null || ifEntityIsGone) && this.isInGround() && this.canBePickedUp)  {
            this.pickupType = PickupPermission.ALLOWED;
        } else {
            this.pickupType = PickupPermission.CREATIVE_ONLY;
        }
        super.tick();
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        //this.playSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 0.1F, 0.3F + (this.random.nextFloat() * 0.4F));
        super.onBlockHit(blockHitResult);
    }
}
