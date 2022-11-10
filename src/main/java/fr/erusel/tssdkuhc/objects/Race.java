package fr.erusel.tssdkuhc.objects;

import fr.erusel.tssdkuhc.enums.RaceStages;
import org.bukkit.entity.Player;

public abstract class Race {

    private final String name;
    private final String description;
    private final RaceStages stage;

    public Race(String name, String description, RaceStages stage) {
        this.name = name;
        this.description = description;
        this.stage = stage;
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

    public abstract void onGive(Player player);


}
