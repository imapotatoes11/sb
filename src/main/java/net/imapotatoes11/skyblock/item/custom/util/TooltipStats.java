package net.imapotatoes11.skyblock.item.custom.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class TooltipStats {

    public int GEAR_SCORE=0;

    // red stats
    public int DAMAGE=0;
    public int STRENGTH=0;
    public int CC=0;
    public int CD=0;
    public int ATKSPD=0;
    public int ABILITY_DMG=0;
    public int SCC=0; // sea creature chance

    // green stats
    public int HEALTH=0;
    public int DEF=0;
    public int SPEED=0;
    public int INTELLIGENCE=0;
    public int MAGIC_FIND=0;
    public int PET_LUCK=0;
    public int TRUE_DEF=0;
    public int FEROCITY=0;
    public int MINING_SPEED=0;
    public int MINING_FORTUNE=0;
    public int FARMING_FORTUNE=0;
    public int PRISTINE=0;
    public int COMBAT_WISDOM=0;
    public int MINING_WISDOM=0;
    public int FARMING_WISDOM=0;
    public int FORAGING_WISDOM=0;
    public int FISHING_WISDOM=0;
    public int ENCHANTING_WISDOM=0;
    public int ALCHEMY_WISDOM=0;
    public int FISHING_SPEED=0;
    public int HEALTH_REGEN=0;
    public int VITALITY=0;
    public int MENDING=0;

    public boolean IS_DUNGEON = false;
    public boolean IS_RECOMBED = false;
    public String WEAPON_TYPE = "SWORD";
    public String RARITY = "LEGENDARY";
    public String RARITY_COLOR = Colors.GOLD;
    public ArrayList<String> ENCHANTS = new ArrayList<String>();
    public boolean IS_REFORGABLE=true;
    public TooltipStats(){}
    public TooltipStats(String weaponType, boolean isDungeonWeapon, boolean isRecombed, String rarity, String rarityColor){
        WEAPON_TYPE=weaponType;
        IS_DUNGEON=isDungeonWeapon;
        IS_RECOMBED=isRecombed;
        RARITY=rarity;
        RARITY_COLOR=rarityColor;
    }
    public TooltipStats(String weaponType, boolean isDungeonWeapon, boolean isRecombed, String rarity, String rarityColor, boolean isReforgable){
        WEAPON_TYPE=weaponType;
        IS_DUNGEON=isDungeonWeapon;
        IS_RECOMBED=isRecombed;
        RARITY=rarity;
        RARITY_COLOR=rarityColor;
        IS_REFORGABLE=isReforgable;
    }
    private String formatNumber(int n){
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        return formatter.format(n);
    }

    public static String manaCost(double amt){
        return "§"+Colors.DARK_GRAY+"Mana Cost: §"+Colors.DARK_AQUA+amt;
    }

    public static String cooldown(double amt){
        return "§"+Colors.DARK_GRAY+"Cooldown: §"+Colors.GREEN+amt+"s";
    }

    private String formatLine(String label, String color, int value, boolean addPercent){
        if (value!=0) return "§7" + label + ": §" + color + this.formatNumber(value) + ( (addPercent) ? "%" : "" );
        else return "null";
    }

    public String[] export(){
        // return array with each element = one line for tooltip
        ArrayList<String> a = new ArrayList<String>();

//        if (GEAR_SCORE!=0) a.add("§7Gear Score: §d"+GEAR_SCORE);
//        if (DAMAGE!=0) a.add("§7Damage: §c"+DAMAGE);
        a.add(formatLine("Gear Score","d", GEAR_SCORE, false));
        a.add(formatLine("Damage", "c", DAMAGE, false));
        a.add(formatLine("Strength", "c", STRENGTH, false));
        a.add(formatLine("Crit Chance", "c", CC, true));
        a.add(formatLine("Crit Damage", "c", CD, true));
        a.add(formatLine("Bonus Attack Speed", "c", ATKSPD, true));
        a.add(formatLine("Ability Damage", "c", ABILITY_DMG, true));
        a.add(formatLine("Sea Creature Chance","c", SCC, true));

        a.add("");

        a.add(formatLine("Health", "a", HEALTH, false));
        a.add(formatLine("Defense", "a", DEF, false));
        a.add(formatLine("Speed", "a", SPEED, false));
        a.add(formatLine("Intelligence", "a", INTELLIGENCE, false));
        a.add(formatLine("Magic Find", "a", MAGIC_FIND, false));
        a.add(formatLine("Pet Luck", "a", PET_LUCK, false));
        a.add(formatLine("True Defense", "a", TRUE_DEF, false));
        a.add(formatLine("Ferocity", "a", FEROCITY, false));
        a.add(formatLine("Mining Speed", "a", MINING_SPEED, false));
        a.add(formatLine("Mining Fortune", "a", MINING_FORTUNE, false));
        a.add(formatLine("Pristine", "a", PRISTINE, false));
        a.add(formatLine("Combat Wisdom", "a", COMBAT_WISDOM, false));
        a.add(formatLine("Mining Wisdom", "a", MINING_WISDOM, false));
        a.add(formatLine("Farming Wisdom", "a", FARMING_WISDOM, false));
        a.add(formatLine("Foraging Wisdom", "a", FORAGING_WISDOM, false));
        a.add(formatLine("Fishing Wisdom", "a", FISHING_WISDOM, false));
        a.add(formatLine("Enchanting Wisdom", "a", ENCHANTING_WISDOM, false));
        a.add(formatLine("Alchemy Wisdom", "a", ALCHEMY_WISDOM, false));
        a.add(formatLine("Health Regen", "a", HEALTH_REGEN, false));
        a.add(formatLine("Vitality", "a", VITALITY, false));
        a.add(formatLine("Mending", "a", MENDING, false));

        if (!ENCHANTS.isEmpty())
            a.add("");

        if (ENCHANTS.size() % 2 != 0) ENCHANTS.add("");

        String[] enchants = ENCHANTS.toArray(String[]::new);
        for (int i=0;i<enchants.length-1; i+=2){
            a.add(enchants[i]+", "+enchants[i+1]);
        }
        // last enchantment
//        a.add(lastEnchant);

        if (!ENCHANTS.isEmpty())
            a.add("");

        // item details
        a.add("add_details_here");


        a.add("");

        if (IS_REFORGABLE) a.add("§"+Colors.DARK_GRAY+"This item can be reforged!");

        String obf = "§" + Colors.OBFUSCATE + "-" + "§r§" + RARITY_COLOR;
        a.add( "§" + RARITY_COLOR + ((IS_RECOMBED) ? obf + " " : " ") + RARITY + ((IS_DUNGEON) ? " DUNGEON " : " ") + WEAPON_TYPE + " " + ((IS_RECOMBED) ? obf + " " : ""));

        return a.toArray(String[]::new);
    }

    public TooltipStats addEnchant(String enchant, String color){
        ENCHANTS.add("§"+color+enchant);
        return this;
    }

    public TooltipStats add(String item, int amt){
        // string item in lowercase snake case
        switch (item){
            case "gear_score": this.GEAR_SCORE=amt; return this;

            case "damage": this.DAMAGE=amt; return this;
            case "dmg": this.DAMAGE=amt; return this;
            case "strength": this.STRENGTH=amt; return this;
            case "cc": this.CC=amt; return this;
            case "crit_chance": this.CC=amt; return this;
            case "cd": this.CD=amt; return this;
            case "crit_damage": this.CD=amt; return this;
            case "atk_speed": this.ATKSPD=amt; return this;
            case "atkspd": this.ATKSPD=amt; return this;
            case "ability_damage": this.ABILITY_DMG=amt; return this;
            case "ability_dmg": this.ABILITY_DMG=amt; return this;
            case "scc": this.SCC=amt; return this;
            case "sea_creature_chance": this.SCC=amt; return this;

            case "health": this.HEALTH=amt; return this;
            case "def": this.DEF=amt; return this;
            case "defense": this.DEF=amt; return this;
            case "speed": this.SPEED=amt; return this;
            case "intelligence": this.INTELLIGENCE=amt; return this;
            case "magic_find": this.MAGIC_FIND=amt; return this;
            case "pet_luck": this.PET_LUCK=amt; return this;
            case "true_defense": this.TRUE_DEF=amt; return this;
            case "ferocity": this.FEROCITY=amt; return this;
            case "mining_speed": this.MINING_SPEED=amt; return this;
            case "mining_fortune": this.MINING_FORTUNE=amt; return this;
            case "farming_fortune": this.FARMING_FORTUNE=amt; return this;
            case "pristine": this.PRISTINE=amt; return this;
            case "combat_wisdom": this.COMBAT_WISDOM=amt; return this;
            case "mining_wisdom": this.MINING_WISDOM=amt; return this;
            case "farming_wisdom": this.FARMING_WISDOM=amt; return this;
            case "foraging_wisdom": this.FORAGING_WISDOM=amt; return this;
            case "fishing_wisdom": this.FISHING_WISDOM=amt; return this;
            case "enchanting_wisdom": this.ENCHANTING_WISDOM=amt; return this;
            case "alchemy_wisdom": this.ALCHEMY_WISDOM=amt; return this;
            case "fishing_speed": this.FISHING_SPEED=amt; return this;
            case "health_regen": this.HEALTH_REGEN=amt; return this;
            case "vitality": this.VITALITY=amt; return this;
            case "mending": this.MENDING=amt; return this;
            default: return this;
        }
    }
}
