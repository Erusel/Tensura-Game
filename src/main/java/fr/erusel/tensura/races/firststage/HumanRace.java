package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.SaintRace;
import org.bukkit.entity.Player;

public class HumanRace extends Race {


    public HumanRace() {
        super("Human", "Human description", RaceStages.FIRSTSTAGE, SaintRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}
