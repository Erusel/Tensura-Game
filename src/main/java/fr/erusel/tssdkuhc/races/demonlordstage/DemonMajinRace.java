package fr.erusel.tssdkuhc.races.demonlordstage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DemonMajinRace extends Race {


    public DemonMajinRace() {
        super("Demon Majin", "DemonMajin description", RaceStages.DEMONLORDSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
