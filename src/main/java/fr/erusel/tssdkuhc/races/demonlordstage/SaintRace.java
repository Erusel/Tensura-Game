package fr.erusel.tssdkuhc.races.demonlordstage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class SaintRace extends Race {


    public SaintRace() {
        super("Saint", "Saint description", RaceStages.DEMONLORDSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
