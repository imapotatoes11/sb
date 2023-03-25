package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.time.Instant;
import java.util.List;

public class WeaponFireVeilWandBig extends Item {

    private boolean isOn = false;

    private long lastUse = Instant.now().getEpochSecond();

    public WeaponFireVeilWandBig(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (Instant.now().getEpochSecond() - lastUse > 1){
            isOn=!isOn;
        }

        lastUse = Instant.now().getEpochSecond();

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected && isOn) {
            // particles
            for (int x = -100; x <= 100; x+=5) {
                for (int y = -100; y <= 100; y+=5) {
                    for (int z = -100; z <= 100; z+=5) {
                        world.addParticle(
                                ParticleTypes.FLAME,
                                (entity.getX()) + x,
                                (entity.getY()) + y,
                                (entity.getZ()) + z,
                                0, 0, 0
                        );
                    }
                }
            }
            // damage
            List<Entity> entities = world.getOtherEntities(entity, entity.getBoundingBox().expand(100));
            for (Entity listEntity : entities) {
                if (listEntity.getType()!= EntityType.ITEM)
                    listEntity.damage(new DamageSource(entity.getName().getString()), 10);
            }
        } else if (isOn) {
            isOn = false;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

}
