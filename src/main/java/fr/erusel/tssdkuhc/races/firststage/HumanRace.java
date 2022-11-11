package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.races.demonlordstage.SaintRace;
import org.bukkit.entity.Player;

public class HumanRace extends Race {


    public HumanRace() {
        super("Human", "Human description", RaceStages.FIRSTSTAGE, SaintRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}
