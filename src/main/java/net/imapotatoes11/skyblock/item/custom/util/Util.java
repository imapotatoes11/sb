package net.imapotatoes11.skyblock.item.custom.util;

import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Util {
    public static final Vec3d VEC_0 = new Vec3d(0,0,0);
    public static void teleport(PlayerEntity user, int distance){
        Vec3d unitVector = user.getRotationVector();
        unitVector = unitVector.normalize().multiply(distance);
        user.move(MovementType.SELF, unitVector);
        user.fallDistance = 0F;
        user.setVelocity(0, 0, 0);
    }
}
