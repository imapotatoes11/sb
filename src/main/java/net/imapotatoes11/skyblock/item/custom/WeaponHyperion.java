package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.client.item.TooltipContext;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
//        if (hitResult.getType() == HitResult.Type.BLOCK)
//            user.sendMessage(Text.of("§cThere are blocks in the way!"));

        Util.teleport(user, 10);

        if (!world.isClient()) {
            world.createExplosion(
                    user,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    10.0f,
                    World.ExplosionSourceType.NONE
            );
            if (hitResult.getType() == HitResult.Type.BLOCK)
                user.sendMessage(Text.of("§cThere are blocks in the way!"));
        }
        user.playSound(
                SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE,
                SoundCategory.PLAYERS,
                1F,
                1.5F
        );
        MyStatusEffect resistance = new MyStatusEffect(StatusEffects.RESISTANCE, 200, 3);
        MyStatusEffect absorption = new MyStatusEffect(StatusEffects.ABSORPTION, 200, 4);
        MyStatusEffect saturation = new MyStatusEffect(StatusEffects.SATURATION, 200, 5);
        user.addStatusEffect(absorption);
        user.addStatusEffect(resistance);
        user.addStatusEffect(saturation);
        user.heal(10.0F);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // this took too long
        tooltip.add(Text.translatable("§7Gear Score: §d4043"));
        tooltip.add(Text.translatable("§7Damage: §c+1,216.3 §e(+30)"));
        tooltip.add(Text.translatable("§7Strength: §c+936.1 §e(+30) §9(Heroic +50)"));
        tooltip.add(Text.translatable("§7Crit Damage: §c+284.9%"));
        tooltip.add(Text.translatable("§7Bonus Attack Speed: §c+10.5% §9(Heroic +7%)"));
        tooltip.add(Text.translatable("§7Ability Damage: §c+108.533%"));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("§7Intelligence: §a+2,547.917 §9(Heroic +125)"));
        tooltip.add(Text.translatable("§7Ferocity: §a+52.5"));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("§dChimera LXVI, §5Cleave VI"));
        tooltip.add(Text.translatable("§6Critical VII, §5Cubism VI"));
        tooltip.add(Text.translatable("§6Dragon Hunter V, Ender Slayer VII"));
        tooltip.add(Text.translatable("§5Execute VI, Experience IV"));
        tooltip.add(Text.translatable("§6Giant Killer VII, §9Impaling III"));
        tooltip.add(Text.translatable("§6Lethality VI, §5Looting V"));
        tooltip.add(Text.translatable("§6Luck VII, Scavenger V"));
        tooltip.add(Text.translatable("§6Smite VII, §9Syphon V"));
        tooltip.add(Text.translatable("§9Telekinesis I, §6Thunderlord VI"));
        tooltip.add(Text.translatable("§9Triple-Strike V, §6Vampirism VI"));
        tooltip.add(Text.translatable("§5Venomous VI, §9Vicious V"));
        tooltip.add(Text.translatable(""));
        // add music rune here if u want
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("§7Deals +§c50%§r§7 damage to"));
        tooltip.add(Text.translatable("§7Withers, Grants §c+1 Damage"));
        tooltip.add(Text.translatable("§7and §a+2 §bIntelligence"));
        tooltip.add(Text.translatable("§7per §cCatacombs level."));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("§aScroll Abilities:"));
        tooltip.add(Text.translatable("§6Item Ability: Wither Impact §eRIGHT CLICK"));
        tooltip.add(Text.translatable("§7Teleport §a10 blocks ahead of"));
        tooltip.add(Text.translatable("§7you. Then implode dealing"));
        tooltip.add(Text.translatable("§7§c5,517,819.5 §r§7damage to nearby"));
        tooltip.add(Text.translatable("§7enemies. Also applies the wither"));
        tooltip.add(Text.translatable("§7shield scroll ability reducing"));
        tooltip.add(Text.translatable("§7damage taken and granting an"));
        tooltip.add(Text.translatable("§7absorption shield for §e5"));
        tooltip.add(Text.translatable("§7seconds"));
        tooltip.add(Text.translatable("§8Mana Cost: §30"));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("§d§k-§r§d MYTHIC DUNGEON SWORD §k-§r"));
        super.appendTooltip(stack, world, tooltip, context);
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