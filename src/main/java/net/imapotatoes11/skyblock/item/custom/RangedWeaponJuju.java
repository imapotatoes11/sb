package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Colors;
import net.imapotatoes11.skyblock.item.custom.util.TooltipStats;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class RangedWeaponJuju extends Item {

    public TooltipStats tooltipStats = new TooltipStats("BOW", true, true, "MYTHIC", Colors.LIGHT_PURPLE)
            .add("damage",310)
            .add("strength",40)
            .add("cc",10)
            .add("cd",110)
            .addEnchant("Growth VII", Colors.GOLD)
            .addEnchant("Protection VII", Colors.GOLD)
            .addEnchant("Efficiency V", Colors.BLUE);

    public RangedWeaponJuju(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ArrowEntity arrowEntity = new ArrowEntity(EntityType.ARROW, world);
            arrowEntity.teleport(
                    user.getX(),
                    user.getY() + 1.6,
                    user.getZ()
            );

            // Can mess with these two in the future
            arrowEntity.setNoGravity(false);
            arrowEntity.setNoClip(false);

            arrowEntity.setVelocity(0, 0, 0);
            arrowEntity.addVelocity(user.getRotationVector().multiply(5));

            world.spawnEntity(arrowEntity);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String[] exportedText = tooltipStats.export();
        for (String line: exportedText){
            if (Objects.equals(line,"null")) continue;
            if (Objects.equals(line,"add_details_here")){
                // add item info here
                tooltip.add(Text.of("§"+ Colors.DARK_PURPLE+"Shortbow: Instantly Shoots"));
                tooltip.add(Text.of("§"+Colors.GRAY+"Hits §"+Colors.RED+"3§"+Colors.GRAY+" mobs on impact."));
                tooltip.add(Text.of("§"+Colors.GRAY+"Cannot damage endermen."));
                continue;
            }
            tooltip.add(Text.of(line));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
