package net.imapotatoes11.skyblock.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ToolGrapplingHook extends Item {
    public ToolGrapplingHook(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // TODO: the bobber doesnt work, im just giving the player some velocity rn
        user.addVelocity(user.getRotationVector().multiply(5).add(0,2.5,0));
        user.sendMessage(Text.of("(fishing bobber code is broken, will fix *soon*)"),true);
//        ItemStack itemStack = user.getStackInHand(hand);
//        int i;
//        if (user.fishHook != null) {
//            if (!world.isClient) {
//                i = user.fishHook.use(itemStack);
//                user.sendMessage(Text.of("cast 1"));
//            }
//
//            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
//            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
//        } else {
//            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
//            if (!world.isClient) {
//                i = EnchantmentHelper.getLure(itemStack);
//                int j = EnchantmentHelper.getLuckOfTheSea(itemStack);
//                world.spawnEntity(new FishingBobberEntity(user, world, j, i));
//                user.sendMessage(Text.of("cast 2"));
//            }
//
//            user.incrementStat(Stats.USED.getOrCreateStat(this));
//            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
//        }
        return super.use(world, user, hand);
    }
}
