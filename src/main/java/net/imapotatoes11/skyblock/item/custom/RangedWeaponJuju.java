package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RangedWeaponJuju extends Item {
    public RangedWeaponJuju(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrowEntity arrowEntity = new ArrowEntity(EntityType.ARROW, world);
        arrowEntity.teleport(
                user.getX(),
                user.getY() + 1.6,
                user.getZ()
        );

        // Can mess with these two in the future
        arrowEntity.setNoGravity(false);
        arrowEntity.setNoClip(false);

        arrowEntity.setVelocity(0,0,0);
        arrowEntity.addVelocity(user.getRotationVector().multiply(5));

        world.spawnEntity(arrowEntity);
        return super.use(world, user, hand);
    }
}
