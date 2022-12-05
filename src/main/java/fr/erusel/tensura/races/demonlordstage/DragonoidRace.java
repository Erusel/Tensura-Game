package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import org.bukkit.entity.Player;

public class DragonoidRace extends Race {


    public DragonoidRace() {
        super("Dragonoid", "Dragonoid description", RaceStages.DEMONLORDSTAGE, Races.DRAGONOID,null);
    }

    @Override
    public void onGive(Player player) {

    }
}
