package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class WeaponHyperion extends Item {
    public WeaponHyperion(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult hitResult = user.raycast(
                10,
                0.0f,
                false
        );
        if(hitResult.getType()== HitResult.Type.BLOCK)
            user.sendMessage((Text) new TranslatableTextContent("§cThere are blocks in the way!"));

        Util.teleport(user);

        world.createExplosion(
                user,
                user.getX(),
                user.getY(),
                user.getZ(),
                10.0f,
                World.ExplosionSourceType.NONE
        );
        user.playSound(
                SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE,
                SoundCategory.PLAYERS,
                1F,
                1.5F
        );
        MyStatusEffect resistance=new MyStatusEffect(StatusEffects.RESISTANCE,200,3);
        MyStatusEffect absorption=new MyStatusEffect(StatusEffects.ABSORPTION,200,4);
        MyStatusEffect saturation=new MyStatusEffect(StatusEffects.SATURATION,200,5);
        user.addStatusEffect(absorption);
        user.addStatusEffect(resistance);
        user.addStatusEffect(saturation);
        user.heal(10.0F);
        return super.use(world, user, hand);
    }
}

class MyStatusEffect extends StatusEffectInstance{
    public MyStatusEffect(
            StatusEffect type,
            int duration,
            int amplifier
    ){
        super(type,duration,amplifier);
    }
}