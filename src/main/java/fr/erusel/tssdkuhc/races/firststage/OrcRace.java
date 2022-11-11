package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.races.demonlordstage.DemonOrcRace;
import org.bukkit.entity.Player;

public class OrcRace extends Race {


    public OrcRace() {
        super("Orc", "Orc description", RaceStages.FIRSTSTAGE, DemonOrcRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}
