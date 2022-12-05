package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import org.bukkit.entity.Player;

public class DemonOrcRace extends Race {


    public DemonOrcRace() {
        super("Demon Orc", "DemonOrc description", RaceStages.DEMONLORDSTAGE, Races.DEMON_ORC,null);
    }

    @Override
    public void onGive(Player player) {

    }
}
