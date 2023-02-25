package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonLordRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class DemonRace extends Race {


    public DemonRace() {
        super("Demon", "Demon description", RaceStages.FIRSTSTAGE, Races.DEMON, DemonLordRace.class);
    }

    @Override
    public void onGive(Player player) {
        // 5% chance to set the attacked opponent in fire for 10sec
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
    }
}
