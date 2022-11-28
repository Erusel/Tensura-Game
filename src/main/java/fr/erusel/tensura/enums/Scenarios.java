package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.scenarios.AutoSmeltScenario;
import fr.erusel.tensura.scenarios.EnchantlessScenario;
import fr.erusel.tensura.scenarios.FirelessScenario;
import fr.erusel.tensura.scenarios.LavalessScenario;

import java.lang.reflect.InvocationTargetException;

public enum Scenarios {

    AUTO_SMELT("Auto Smelt","Automatically smelt ore when mined", AutoSmeltScenario.class),
    ENCHANTLESS("Enchantless", "Disable enchanting table", EnchantlessScenario.class),
    LAVALESS("Lavaless", "Lava damage are disabled", LavalessScenario.class),
    FIRELESS("Fireless","Fire damage are disabled", FirelessScenario.class);






    private final String name;
    private final String description;
    private final Class<? extends Scenario> scenarioClass;

    Scenarios(String name,String description,  Class<? extends Scenario> scenarioClass) {
        this.name = name;
        this.description = description;
        this.scenarioClass = scenarioClass;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Class<? extends Scenario> getScenarioClass() {
        return scenarioClass;
    }

    public Scenario createInstance(){
        try {
            return getScenarioClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
