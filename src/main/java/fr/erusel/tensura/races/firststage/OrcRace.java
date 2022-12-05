package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonOrcRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class OrcRace extends Race {


    public OrcRace() {
        super("Orc", "Orc description", RaceStages.FIRSTSTAGE, Races.ORC, DemonOrcRace.class);
    }

    @Override
    public void onGive(Player player) {
        // More Life
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24);
    }
}
