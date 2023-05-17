package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Colors;
import net.imapotatoes11.skyblock.item.custom.util.TooltipStats;
import net.imapotatoes11.skyblock.item.custom.util.Util;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class WeaponAspectOfTheVoid extends Item {

    public TooltipStats tooltipStats = new TooltipStats("SWORD", false, false, "EPIC", Colors.DARK_PURPLE)
            .add("damage", 120)
            .add("strength", 100);

    public WeaponAspectOfTheVoid(Settings settings){
        super(settings);
    }

    public static final int ETHERWARP_RANGE = 57;
    public static final int TELEPORT_RANGE = 12;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            HitResult hitResult = user.raycast(ETHERWARP_RANGE, 1.0F, false);
            switch (hitResult.getType()) {
                case BLOCK:
                    boolean b1=world.getBlockState(
                            new BlockPos(
                                    hitResult.getPos().add(new Vec3d(0,1,0))
                            )
                    ).isAir();
                    boolean b2=world.getBlockState(
                            new BlockPos(
                                    hitResult.getPos().add(new Vec3d(0,2,0))
                            )
                    ).isAir();
                    if (b1 && b2){
                        user.teleport(
                                ((int)hitResult.getPos().getX()) + 0.5d,
                                hitResult.getPos().getY()+0.75,
                                ((int)hitResult.getPos().getZ()) + 0.5d
                        );
                    }
            }
        } else{
            HitResult hitResult = user.raycast(12,0.0f,false);
            switch (hitResult.getType()) {
                case BLOCK:
                    user.sendMessage(Text.of("§cThere are blocks in the way!"));
            }
            Util.teleport(user,TELEPORT_RANGE);
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
                tooltip.add(Text.of("§"+ Colors.GOLD+"Ability: Instant Transmission "+"§"+Colors.YELLOW+"RIGHT CLICK"));
                tooltip.add(Text.of("§"+Colors.GRAY+"Teleport §"+Colors.GREEN+"8 blocks §"+Colors.GRAY+"ahead of"));
                tooltip.add(Text.of("§"+Colors.GRAY+"you and gain +50 §"+Colors.WHITE+"Speed"));
                tooltip.add(Text.of("§"+Colors.GRAY+"for §"+Colors.GREEN+"3 seconds§"+Colors.GRAY+"."));
                tooltip.add(Text.of(TooltipStats.manaCost(0)));

                tooltip.add(Text.of(""));
                tooltip.add(Text.of("§"+ Colors.GOLD+"Ability: Ether Transmission "+"§"+Colors.YELLOW+"SNEAK RIGHT CLICK"));
                tooltip.add(Text.of("§"+Colors.GRAY+"Teleport to the targeted block"));
                tooltip.add(Text.of("§"+Colors.GRAY+"up to §"+Colors.GREEN+"60 blocks§r§"+Colors.GRAY+" away."));
                tooltip.add(Text.of(TooltipStats.manaCost(0).replaceFirst("Mana", "Soulflow")));
                tooltip.add(Text.of(TooltipStats.manaCost(0)));
                continue;
            }
            tooltip.add(Text.of(line));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
