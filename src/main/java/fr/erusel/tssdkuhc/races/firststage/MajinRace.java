package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.races.demonlordstage.DemonMajinRace;
import org.bukkit.entity.Player;

public class MajinRace extends Race {


    public MajinRace() {
        super("Majin", "Majin description", RaceStages.FIRSTSTAGE, DemonMajinRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}
