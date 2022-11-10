package fr.erusel.tssdkuhc.races.demonlordstage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DemonLordRace extends Race {


    public DemonLordRace() {
        super("Demon Lord", "DemonLord description", RaceStages.DEMONLORDSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
