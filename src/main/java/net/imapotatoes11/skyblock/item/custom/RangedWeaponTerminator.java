package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RangedWeaponTerminator extends Item {
    public RangedWeaponTerminator(Settings settings){
        super(settings);
    }

    private static ArrowEntity setupArrow(World world, PlayerEntity user, int direction){
        // direction:
        // 0 = none
        // -1 = left
        // 1 = right

        ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, world);
//        arrow.teleport(
//                user.getX(),
//                user.getY()+1.6,
//                user.getZ()
//        );
        if (user.isSneaking()) {
            arrow.teleport(user.getX(), user.getY() + 1.3, user.getZ());
        } else {
            arrow.teleport(user.getX(), user.getY() + 1.6, user.getZ());
        }

        //these two are not needed, but values can be changed to spice things up a bit
        arrow.setNoGravity(false);
        arrow.setNoClip(false);

        arrow.setVelocity(Util.VEC_0);
        switch (direction){
            case 1:
                arrow.addVelocity(user.getRotationVector().multiply(5).rotateY((float) Math.toRadians(-15)));
            case 0:
                arrow.addVelocity(user.getRotationVector().multiply(5));
            case -1:
                arrow.addVelocity(user.getRotationVector().multiply(5).rotateY((float) Math.toRadians(15)));
        }
        return arrow;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrowEntity middleArrow = new ArrowEntity(EntityType.ARROW, world);
        middleArrow.teleport(
                user.getX(),
                user.getY() + 1.6,
                user.getZ()
        );

        // Can mess with these two in the future
        middleArrow.setNoGravity(false);
        middleArrow.setNoClip(false);

        middleArrow.setVelocity(0,0,0);
        middleArrow.addVelocity(user.getRotationVector().multiply(5));

        ArrowEntity leftArrow = new ArrowEntity(EntityType.ARROW, world);
        leftArrow.teleport(
                user.getX(),
                user.getY()+1.6,
                user.getZ()
        );
        leftArrow.setNoGravity(false);
        leftArrow.setNoClip(false);

        leftArrow.setVelocity(0,0,0);
        // make arrow go around 30 degrees to the left of the player's rotation vector
        leftArrow.addVelocity(user.getRotationVector().multiply(5).rotateY((float) Math.toRadians(15)));

        ArrowEntity rightArrow = new ArrowEntity(EntityType.ARROW, world);
        rightArrow.teleport(
                user.getX(),
                user.getY()+1.6,
                user.getZ()
        );
        rightArrow.setNoGravity(false);
        rightArrow.setNoClip(false);

        rightArrow.setVelocity(0,0,0);
        // make arrow go around 30 degrees to the right of the player's rotation vector
        rightArrow.addVelocity(user.getRotationVector().multiply(5).rotateY((float) Math.toRadians(-15)));

        // this breaks it??? what??
//        world.spawnEntity(setupArrow(world, user, 0));
//        world.spawnEntity(setupArrow(world, user, -1));
//        world.spawnEntity(setupArrow(world, user, 1));

        world.spawnEntity(middleArrow);
        world.spawnEntity(leftArrow);
        world.spawnEntity(rightArrow);
        return super.use(world, user, hand);
    }
}
