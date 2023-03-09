package net.imapotatoes11.skyblock.item.custom.util;

import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Util {
    public static void teleport(PlayerEntity user){
        Vec3d unitVector = user.getRotationVector();
        unitVector = unitVector.normalize().multiply(10);
        user.move(MovementType.SELF, unitVector);
        user.fallDistance = 0F;
        user.setVelocity(0, 0, 0);
    }
}
