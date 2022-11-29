package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonLordRace;
import org.bukkit.entity.Player;

public class DemonRace extends Race {


    public DemonRace() {
        super("Demon", "Demon description", RaceStages.FIRSTSTAGE, DemonLordRace.class);
    }

    @Override
    public void onGive(Player player) {
        // 5% chance to set the attacked opponent in fire for 10sec
    }
}
