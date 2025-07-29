package com.portix.pricksnplanks.item.custom;

import com.portix.pricksnplanks.entity.ModEntityTypes;
import com.portix.pricksnplanks.entity.custom.ThrowableCactusEntity;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ThrowableCactusItem extends Item implements ProjectileItem { //implements ProjectileItem
    public ThrowableCactusItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        world.playSound(
                null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WITCH_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F + world.random.nextFloat() * 0.4F
        );
        if (world instanceof ServerWorld serverWorld) {
            //ProjectileEntity.spawnWithVelocity(ThrowableCactusEntity::new, serverWorld, itemStack, player, 0.0F, 1.0F, 0.5F);
            ThrowableCactusEntity ball = new ThrowableCactusEntity(world, player);
            ball.setPosition(player.getX(), player.getEyeY() - 0.1, player.getZ());
            Vec3d speed = player.getRotationVector();
            speed.add(player.getMovement().x, player.isOnGround() ? 0f : player.getMovement().y, player.getMovement().z);
            ball.setVelocity(speed.x, speed.y, speed.z, 1.0f, 1.0f);
            if (player.isCreative()) {
                ball.setUnpickupable(true);
                //ball.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
            //ball.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.0F, 1.0F);
            world.spawnEntity(ball);
            //player.sendMessage(Text.literal("Thrown").formatted(Formatting.DARK_GREEN), false);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, player);
        return ActionResult.SUCCESS;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        ThrowableCactusEntity throwableCactusEntity = new ThrowableCactusEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
        //throwableCactusEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return throwableCactusEntity;
    }

    @Override
    public void initializeProjectile(ProjectileEntity entity, double x, double y, double z, float power, float uncertainty) {
        ProjectileItem.super.initializeProjectile(entity, x, y, z, power, uncertainty);
    }

    /*
    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new ThrowableCactusEntity(world, stack.getHolder(), pos.getX(), pos.getY(), pos.getZ(), direction.getDoubleVector(), 1.0f);
    }
     */
}
