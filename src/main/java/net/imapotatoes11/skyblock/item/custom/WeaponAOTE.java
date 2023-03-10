package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class WeaponAOTE extends Item {
    public WeaponAOTE(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult hitResult = user.raycast(10,0.0f,false);
        switch (hitResult.getType()) {
            case BLOCK:
                user.sendMessage(Text.of("§cThere are blocks in the way!"));
        }
        Util.teleport(user,12);
        return super.use(world, user, hand);
    }
}
