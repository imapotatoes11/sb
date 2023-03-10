package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WeaponAspectOfTheVoid extends Item {
    public WeaponAspectOfTheVoid(Settings settings){
        super(settings);
    }

    public static final int ETHERWARP_RANGE = 57;
    public static final int TELEPORT_RANGE = 12;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            HitResult hitResult = user.raycast(ETHERWARP_RANGE, 1.0F, false);
            switch (hitResult.getType()) {
                case BLOCK:
                    boolean b1=world.getBlockState(
                            new BlockPos(
                                    hitResult.getPos().add(new Vec3d(0,1,0))
                            )
                    ).isAir();
                    boolean b2=world.getBlockState(
                            new BlockPos(
                                    hitResult.getPos().add(new Vec3d(0,2,0))
                            )
                    ).isAir();
                    if (b1 && b2){
                        user.teleport(
                                hitResult.getPos().getX(),
                                hitResult.getPos().getY(),
                                hitResult.getPos().getZ()
                        );
                    }
            }
        } else{
            HitResult hitResult = user.raycast(12,0.0f,false);
            switch (hitResult.getType()) {
                case BLOCK:
                    user.sendMessage(Text.of("§cThere are blocks in the way!"));
            }
            Util.teleport(user,12);
        }
        return super.use(world, user, hand);
    }
}
