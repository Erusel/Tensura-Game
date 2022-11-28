package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonMajinRace;
import org.bukkit.entity.Player;

public class MajinRace extends Race {


    public MajinRace() {
        super("Majin", "Majin description", RaceStages.FIRSTSTAGE, DemonMajinRace.class);
    }

    @Override
    public void onGive(Player player) {
        // 5% chance to give hunger when hit someone BUT take x1.3 damage from smite
    }
}
