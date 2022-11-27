package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.scenarios.AutoSmeltScenario;
import fr.erusel.tensura.scenarios.EnchantlessScenario;
import fr.erusel.tensura.scenarios.FirelessScenario;

import java.lang.reflect.InvocationTargetException;

public enum Scenarios {

    AUTO_SMELT("Auto Smelt", AutoSmeltScenario.class),
    ENCHANTLESS("Enchantless", EnchantlessScenario.class),
    FIRELESS("Fireless", FirelessScenario.class);

    private final String name;
    private final Class<? extends Scenario> scenarioClass;

    Scenarios(String name, Class<? extends Scenario> scenarioClass) {
        this.name = name;
        this.scenarioClass = scenarioClass;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Scenario> getScenarioClass() {
        return scenarioClass;
    }

    public Scenario createInstance(){
        try {
            return scenarioClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
