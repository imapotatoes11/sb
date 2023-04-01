package net.imapotatoes11.skyblock.item.custom;

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

    public TooltipStats tooltipStats;

    public RangedWeaponJuju(Settings settings){
        super(settings);
    }

    public RangedWeaponJuju(Settings settings, TooltipStats tooltipStats){
        super(settings);
        this.tooltipStats = tooltipStats;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrowEntity arrowEntity = new ArrowEntity(EntityType.ARROW, world);
        arrowEntity.teleport(
                user.getX(),
                user.getY() + 1.6,
                user.getZ()
        );

        // Can mess with these two in the future
        arrowEntity.setNoGravity(false);
        arrowEntity.setNoClip(false);

        arrowEntity.setVelocity(0,0,0);
        arrowEntity.addVelocity(user.getRotationVector().multiply(5));

        world.spawnEntity(arrowEntity);

        String[] exportedText = tooltipStats.export();
        for (String line: exportedText){
            user.sendMessage(Text.of(line));
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
                continue;
            }
            tooltip.add(Text.of(line));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
