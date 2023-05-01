package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Colors;
import net.imapotatoes11.skyblock.item.custom.util.TooltipStats;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeaponFireVeilWand extends Item {

    private boolean isOn = false;

//    private long lastUse = Instant.now().getEpochSecond();

    public TooltipStats tooltipStats = new TooltipStats("WAND", false, false, "EPIC", Colors.DARK_PURPLE)
            .add("damage",50)
            .add("intelligence",200);

    public WeaponFireVeilWand(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient()) {
            isOn = !isOn;

//            lastUse = Instant.now().getEpochSecond();
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (selected && isOn) {
                // particles
                for (int x = -6; x <= 6; x += 2) {
                    for (int y = -6; y <= 6; y += 2) {
                        for (int z = -6; z <= 6; z += 2) {
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
                List<Entity> entities = world.getOtherEntities(entity, entity.getBoundingBox().expand(5));
                for (Entity listEntity : entities) {
                    listEntity.damage(new DamageSource(entity.getName().getString()), 10);
                }
            } else if (isOn) {
                isOn = false;
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String[] exportedText = tooltipStats.export();
        for (String line: exportedText){
            if (Objects.equals(line,"null")) continue;
            if (Objects.equals(line,"add_details_here")){
                // add item info here
                tooltip.add(Text.of(""));
                tooltip.add(Text.of("§"+ Colors.GOLD+"Ability: Fire Veil §"+Colors.YELLOW+"RIGHT CLICK"));
                tooltip.add(Text.of("§"+Colors.GRAY+"Creates a veil of fire around"));
                tooltip.add(Text.of("§"+Colors.GRAY+"you for §"+Colors.GREEN+"5s§"+Colors.GRAY+", dealing"));
                tooltip.add(Text.of("§"+Colors.RED+"265,000.5 §"+Colors.GRAY+"damage per second"));
                tooltip.add(Text.of("§"+Colors.GRAY+"to mobs within."));
                tooltip.add(Text.of(TooltipStats.manaCost(0)));
                tooltip.add(Text.of(TooltipStats.cooldown(1)));
                continue;
            }
            tooltip.add(Text.of(line));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
