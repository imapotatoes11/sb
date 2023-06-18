package net.imapotatoes11.skyblock.item.custom;

import net.imapotatoes11.skyblock.item.custom.util.Colors;
import net.imapotatoes11.skyblock.item.custom.util.TooltipStats;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ToolGrapplingHook extends FishingRodItem {

    public TooltipStats tooltipStats = new TooltipStats("ITEM", false, true, "UNCOMMON", Colors.GREEN)
            .add("damage",310)
            .add("strength",40)
            .add("cc",10)
            .add("cd",110)
            .addEnchant("Power VII", Colors.GOLD)
            .addEnchant("Overload V", Colors.GOLD)
            .addEnchant("Critical VII", Colors.GOLD);

    public ToolGrapplingHook(Item.Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String[] exportedText = tooltipStats.export();
        for (String line: exportedText){
            if (Objects.equals(line,"null")) continue;
            if (Objects.equals(line,"add_details_here")){
                // add item info here
                tooltip.add(Text.of("§"+ Colors.DARK_PURPLE+"Caution: Broken"));
                tooltip.add(Text.of("§"+Colors.GRAY+"This §"+Colors.RED+"item§"+Colors.GRAY+" is currently broken."));
                tooltip.add(Text.of("§"+Colors.GRAY+"Please do not use."));
                continue;
            }
            tooltip.add(Text.of(line));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
