package net.imapotatoes11.skyblock.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.imapotatoes11.skyblock.Skyblock;
import net.imapotatoes11.skyblock.item.custom.*;
import net.imapotatoes11.skyblock.item.custom.util.Colors;
import net.imapotatoes11.skyblock.item.custom.util.TooltipStats;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    // cannot use .group() in 1.19 anymore
    // https://www.reddit.com/r/fabricmc/comments/zoc1xj/item_groups/
    // https://fabricmc.net/2022/11/24/1193.html

    public static final Item HYPERION=registerItem("hyperion",new WeaponHyperion(new FabricItemSettings()));
    public static final Item SCYLLA=registerItem("scylla",new WeaponHyperion(new FabricItemSettings()));
    public static final Item ASTREA=registerItem("astrea",new WeaponHyperion(new FabricItemSettings()));
    public static final Item VALKYRIE=registerItem("valkyrie",new WeaponHyperion(new FabricItemSettings()));
    public static final Item SILENT_DEATH=registerItem("silent_death",new WeaponSilentDeath(new FabricItemSettings()));

    public static final Item AOTE=registerItem("aspect_of_the_end",new WeaponAOTE(new FabricItemSettings()));
    public static final Item AOTV=registerItem("aspect_of_the_void",
            new WeaponAspectOfTheVoid(new FabricItemSettings()));

    public static final Item JUJU_BOW=registerItem("juju_shortbow",
            new RangedWeaponJuju(new FabricItemSettings()));
    public static final Item TERMINATOR_BOW=registerItem("terminator_bow",
            new RangedWeaponTerminator(new FabricItemSettings()));

    public static final Item FIRE_VEIL_WAND=registerItem("fire_veil_wand",new WeaponFireVeilWand(new FabricItemSettings()));
    public static final Item FIRE_VEIL_WAND_BIG=registerItem("fire_veil_wand_big",new WeaponFireVeilWandBig(new FabricItemSettings()));
    public static final Item AURORA_STAFF=registerItem("aurora_staff",new WeaponAuroraStaff(new FabricItemSettings()));


    // Tools

    public static final Item GRAPPLING_HOOK=registerItem("grappling_hook",new ToolGrapplingHook(new FabricItemSettings()));
    public static final Item ROGUE_SWORD=registerItem("rogue_sword", new WeaponRogueSword(new FabricItemSettings()));


    private static SwordItem registerSwordItem(String name, SwordItem item){
        return Registry.register(Registries.ITEM,new Identifier(Skyblock.MOD_ID,name),item);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(Skyblock.MOD_ID,name),item);
    }

    public static final ItemGroup SB_ITEMGROUP = FabricItemGroup.builder(
                    new Identifier("skyblock", "skyblock_items")
            )
            .displayName(Text.literal("Skyblock"))
            .icon(() -> new ItemStack(HYPERION))
            .entries(((enabledFeatures, entries, operatorEnabled) -> {
                for (Item entry : new Item[] {
                        HYPERION,
                        VALKYRIE,
                        SCYLLA,
                        ASTREA,
                        JUJU_BOW,
                        TERMINATOR_BOW,
                        AOTE,
                        AOTV,
                        ROGUE_SWORD,
                        GRAPPLING_HOOK,
                        SILENT_DEATH,
                        FIRE_VEIL_WAND,
                        FIRE_VEIL_WAND_BIG,
                        AURORA_STAFF
                }) {
                    entries.add(new ItemStack(entry));
                }
            }))
            .build();

    public static void registerModItems() {
        Skyblock.LOGGER.info("Registered Mod Items for "+Skyblock.MOD_ID+"... ");

//        for (Item item : new Item[] {
//                HYPERION,
//                VALKYRIE,
//                SCYLLA,
//                ASTREA,
//                JUJU_BOW,
//                TERMINATOR_BOW,
//                AOTE,
//                AOTV,
//                ROGUE_SWORD,
//                GRAPPLING_HOOK,
//                SILENT_DEATH,
//                FIRE_VEIL_WAND,
//                FIRE_VEIL_WAND_BIG
//        }) {
//            ItemGroupEvents.modifyEntriesEvent(SB_ITEMGROUP)
//                    .register(entries -> entries.add(new ItemStack(item)));
//        }

        Skyblock.LOGGER.info("Successfully loaded ItemGroup skyblock_items");
    }
}
