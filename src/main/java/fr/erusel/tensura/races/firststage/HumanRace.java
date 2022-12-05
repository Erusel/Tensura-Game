package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.SaintRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HumanRace extends Race {


    public HumanRace() {
        super("Human", "Human description", RaceStages.FIRSTSTAGE, Races.HUMAN, SaintRace.class);
    }

    @Override
    public void onGive(Player player) {
        // Strength x1.2
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+0.2f);
    }
}
