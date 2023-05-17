package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class WeaponAuroraStaff extends Item {
    public WeaponAuroraStaff(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult hitResult = user.raycast(100d, 0.0f, false);
        if (hitResult.getType() == HitResult.Type.ENTITY){
            user.sendMessage(Text.of("is this even getting called?"), true);
            List entityList = new ArrayList<EntityType>();
            entityList.add(EntityType.PLAYER);
            Entity closestEntity = world.getClosestEntity(LivingEntity.class, (TargetPredicate)EntityPredicates.VALID_ENTITY, null, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), new Box(hitResult.getPos().getX() - 10, hitResult.getPos().getY() - 10, hitResult.getPos().getZ() - 10, hitResult.getPos().getX() + 10, hitResult.getPos().getY() + 10, hitResult.getPos().getZ() + 10));
            user.sendMessage(Text.of(closestEntity.getType().getName().getString()), true);
            try {closestEntity.kill();}
            catch (NullPointerException ignored) {}
        }
        return super.use(world, user, hand);
    }
}
