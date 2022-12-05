package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import org.bukkit.entity.Player;

public class DemonSlimeRace extends Race {


    public DemonSlimeRace() {
        super("Demon Slime", "DemonSlime description", RaceStages.DEMONLORDSTAGE, Races.DEMON_SLIME, null);
    }

    @Override
    public void onGive(Player player) {

    }
}
