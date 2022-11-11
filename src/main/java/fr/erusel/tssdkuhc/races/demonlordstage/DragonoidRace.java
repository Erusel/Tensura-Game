package fr.erusel.tssdkuhc.races.demonlordstage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DragonoidRace extends Race {


    public DragonoidRace() {
        super("Dragonoid", "Dragonoid description", RaceStages.DEMONLORDSTAGE, null);
    }

    @Override
    public void onGive(Player player) {

    }
}
