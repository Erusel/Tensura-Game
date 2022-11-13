package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.races.demonlordstage.DemonOrcRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class OrcRace extends Race {


    public OrcRace() {
        super("Orc", "Orc description", RaceStages.FIRSTSTAGE, DemonOrcRace.class);
    }

    @Override
    public void onGive(Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(26);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+1);
    }
}
