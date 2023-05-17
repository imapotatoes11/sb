package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;

public class WeaponSilentDeath extends Item {
    public WeaponSilentDeath(Settings settings){
        super(settings);
    }

    public static final int RANGE = 50; // default 20

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            /// very scuffed code, but it does work

            PlayerInventory inventory = user.getInventory();
            ItemStack stack = inventory.getMainHandStack(); // Get the ItemStack that was used
            if (!world.isClient && user instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) user;

                // Raycast to get the targeted position
                HitResult hitResult = player.raycast(RANGE, 0.0F, false);
                BlockPos pos = new BlockPos(hitResult.getPos());
                if (pos == null) {
                    pos = new BlockPos(hitResult.getPos());
                }

                // Get the closest entity in a 1 block radius to the targeted position
                Box box = new Box(pos).expand(1.0, 1.0, 1.0);
                LivingEntity target = null;
                List<Entity> entities = world.getEntitiesByClass(Entity.class, box, entity -> entity != player && entity.isAlive());
                if (!entities.isEmpty()) {
                    target = (LivingEntity) entities.get(0);
                }

                if (target != null) {
                    // Teleport the player behind the entity
                    double x = target.getX() + Math.sin(target.headYaw * Math.PI / 180.0) * 2.0;
                    double y = target.getY();
                    double z = target.getZ() - Math.cos(target.headYaw * Math.PI / 180.0) * 2.0;
                    player.teleport(x, y, z);

                    // Give the player strength 2 for 10 seconds
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 10, 1));

                    // Play a sound and show a message in chat
                    world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
//                player.sendMessage(Text.of("Teleported behind " + target.getName().getString() + " and gained strength!"), true);
                } else {
                    // Player not looking at an entity
                    player.sendMessage(Text.of("No entities found (try pointing at the ground near entity)"), true);
                }

                // Decrement the item stack by 1
                stack.decrement(1);
            }
        }
        return super.use(world, user, hand);
    }
}
