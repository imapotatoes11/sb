package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Colors;
import net.imapotatoes11.skyblock.item.custom.util.TooltipStats;
import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class WeaponAOTE extends Item {
    public TooltipStats tooltipStats = new TooltipStats("SWORD", false, false, "RARE", Colors.BLUE)
            .add("damage", 60)
            .add("strength", 50);
    public WeaponAOTE(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult hitResult = user.raycast(10, 0.0f, false);
        switch (hitResult.getType()) {
            case BLOCK:
                user.sendMessage(Text.of("§cThere are blocks in the way!"));
        }
        Util.teleport(user, 12);
        AoteEffect speed = new AoteEffect(StatusEffects.SPEED, 100, 1);
        user.addStatusEffect(speed);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String[] exportedText = tooltipStats.export();
        for (String line: exportedText){
            if (Objects.equals(line,"null")) continue;
            if (Objects.equals(line,"add_details_here")){
                // add item info here
                tooltip.add(Text.of("§"+ Colors.GOLD+"Ability: Instant Transmission "+"§"+Colors.YELLOW+"RIGHT CLICK"));
                tooltip.add(Text.of("§"+Colors.GRAY+"Teleport §"+Colors.GREEN+"12 blocks §"+Colors.GRAY+"ahead of"));
                tooltip.add(Text.of("§"+Colors.GRAY+"you and gain +50 §"+Colors.WHITE+"Speed"));
                tooltip.add(Text.of("§"+Colors.GRAY+"for §"+Colors.GREEN+"3 seconds§"+Colors.GRAY+"."));
                tooltip.add(Text.of(TooltipStats.manaCost(0)));
                continue;
            }
            tooltip.add(Text.of(line));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}


class AoteEffect extends StatusEffectInstance {
    public AoteEffect(
            StatusEffect type,
            int duration,
            int amplifier
    )
    {
        super(type, duration, amplifier);
    }
}