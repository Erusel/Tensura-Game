package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.races.demonlordstage.DemonLordRace;
import org.bukkit.entity.Player;

public class DemonRace extends Race {


    public DemonRace() {
        super("Demon", "Demon description", RaceStages.FIRSTSTAGE, DemonLordRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}
