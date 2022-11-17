package fr.erusel.tensura.objects;

import fr.erusel.tensura.enums.RaceStages;
import org.bukkit.entity.Player;

public abstract class Race {

    private final String name;
    private final String description;
    private final RaceStages stage;
    private final Class<?> demonLordStage;

    public Race(String name, String description, RaceStages stage, Class<?> demonLordStage) {
        this.name = name;
        this.description = description;
        this.stage = stage;
        this.demonLordStage = demonLordStage;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public RaceStages getStage() {
        return stage;
    }

    public Class<?> getDemonLordStage() {
        return demonLordStage;
    }

    public abstract void onGive(Player player);


}
