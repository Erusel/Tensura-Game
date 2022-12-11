package fr.erusel.tensura.objects;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import org.bukkit.entity.Player;

public abstract class Race extends GameElement{

    private final String name;
    private final String description;
    private final RaceStages stage;
    private final Class<?> demonLordStage;
    private final Races race;

    public Race(String name, String description, RaceStages stage,Races race, Class<?> demonLordStage) {
        this.name = name;
        this.description = description;
        this.stage = stage;
        this.race = race;
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
    public Races getRace(){
        return race;
    }
    public Class<?> getDemonLordStage() {
        return demonLordStage;
    }

    public abstract void onGive(Player player);


}
