package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.scenarios.AutoSmeltScenario;
import fr.erusel.tensura.scenarios.EnchantlessScenario;
import fr.erusel.tensura.scenarios.FirelessScenario;
import fr.erusel.tensura.scenarios.LavalessScenario;

import java.util.function.Supplier;

public enum Scenarios {

    AUTO_SMELT("Auto Smelt","Automatically smelt ore when mined", AutoSmeltScenario::new),
    ENCHANTLESS("Enchantless", "Disable enchanting table", EnchantlessScenario::new),
    LAVALESS("Lavaless", "Lava damage are disabled", LavalessScenario::new),
    FIRELESS("Fireless","Fire damage are disabled", FirelessScenario::new);


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
