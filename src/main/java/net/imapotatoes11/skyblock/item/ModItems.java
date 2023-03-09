package net.imapotatoes11.skyblock.item;

import net.imapotatoes11.skyblock.Skyblock;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static SwordItem registerSwordItem(String name, SwordItem item){
        return Registry.register(Registries.ITEM,new Identifier(Skyblock.MOD_ID,name),item);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(Skyblock.MOD_ID,name),item);
    }

    public static void registerModItems() {
        Skyblock.LOGGER.info("Registering Mod Items for "+Skyblock.MOD_ID+"... ");
    }
}
