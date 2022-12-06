package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import org.bukkit.entity.Player;

public class DemonLordRace extends Race {


    public DemonLordRace() {
        super("Demon Lord", "DemonLord description", RaceStages.DEMONLORDSTAGE, Races.DEMON_LORD, null);
    }

    @Override
    public void onGive(Player player) {

    }
}
