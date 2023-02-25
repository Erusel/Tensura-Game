package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonMajinRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class MajinRace extends Race {


    public MajinRace() {
        super("Majin", "Majin description", RaceStages.FIRSTSTAGE, Races.MAJIN, DemonMajinRace.class);
    }

    @Override
    public void onGive(Player player) {
        // 5% chance to give hunger when hit someone
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
    }
}
