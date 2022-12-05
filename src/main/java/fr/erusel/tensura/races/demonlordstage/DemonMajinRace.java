package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import org.bukkit.entity.Player;

public class DemonMajinRace extends Race {


    public DemonMajinRace() {
        super("Demon Majin", "DemonMajin description", RaceStages.DEMONLORDSTAGE, Races.DEMON_MAJIN, null);
    }

    @Override
    public void onGive(Player player) {

    }
}
