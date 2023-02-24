package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.scenarios.*;

import java.util.function.Supplier;

public enum Scenarios {

    AUTO_SMELT("Auto Smelt","Automatically smelt ore when mined", AutoSmeltScenario::new),
    BETTERTOOLS("Better Tools","Every tool crafted get efficiency 3", BetterToolsScenario::new),
    BREAKLESS("Breakless","Crafted items can't be broken", BreaklessScenario::new),
    DEADLYHEAL("Deadly Heal","Get heal when you kill a player", DeadlyHealScenario::new),
    DIAMONDLESS("Diamondless","Diamond ores don't drop diamonds but 2 gold instead", DiamondLessScenario::new),
    ENCHANTLESS("Enchantless", "Disable enchanting table", EnchantlessScenario::new),
    ENDERPEARLLESS("EnderpearlLess", "Disable enderpearl", EnderpearlLessScenario::new),
    FIRELESS("Fireless","Fire damage are disabled", FirelessScenario::new),
    FISHINGLESS("FishingLess", "Disable fishing", FishingLessScenario::new),
    GAPPLECOOLDOWN("GappleCooldown", "Gapples have a cooldown of 2 minutes", GappleCooldownScenario::new),
    LAVALESS("Lavaless", "Lava damage are disabled", LavaLessScenario::new),
    MAXHEALTH("MaxHealth", "Max health is 2 hearts bar", MaxHealthScenario::new),
    NIGHTVISION("NightVision", "Every player have night vision", NightvisionScenario::new),
    SNEAKLESS("SneakLess", "Sneaking player receive damage", SneakLessScenario::new),
    STARTINGKIT("StartingKit", "Every player get a kit at the start of the game", StartingKitScenario::new);


    private final String name;
    private final String description;
    private final Supplier<Scenario> scenarioSupplier;

    Scenarios(String name,String description,  Supplier<Scenario> scenarioSupplier) {
        this.name = name;
        this.description = description;
        this.scenarioSupplier = scenarioSupplier;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Scenario createInstance(){
        return scenarioSupplier.get();
    }
}
