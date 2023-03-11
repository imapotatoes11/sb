package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WeaponSilentDeath extends Item {
    public WeaponSilentDeath(Settings settings){
        super(settings);
    }

    public static final int RANGE = 50; // default 20

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult hitResult = user.raycast(RANGE,0.0f,false);
        switch (hitResult.getType()){
            case ENTITY:
                user.sendMessage(Text.of("smth happening"),false);
                Entity entity=world.getClosestEntity(null, TargetPredicate.DEFAULT, null,
                        hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                Vec3d vec3d=entity.getRotationVector();
                user.teleport(vec3d.x, vec3d.y, vec3d.z);
        }
        return super.use(world, user, hand);
    }
}
