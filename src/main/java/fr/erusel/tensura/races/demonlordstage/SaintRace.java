package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class SaintRace extends Race {


    public SaintRace() {
        super("Saint", "Saint description", RaceStages.DEMONLORDSTAGE, Races.SAINT, null);
    }

    @Override
    public void onGive(Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
    }
}
