package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WeaponAspectOfTheVoid extends Item {
    public WeaponAspectOfTheVoid(Settings settings){
        super(settings);
    }

    public static final int ETHERWARP_RANGE = 57;
    public static final int TELEPORT_RANGE = 12;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
