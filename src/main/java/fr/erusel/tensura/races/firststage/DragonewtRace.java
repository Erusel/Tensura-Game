package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DragonoidRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class DragonewtRace extends Race {


    public DragonewtRace() {
        super("Dragonewt", "Dragonewt description", RaceStages.FIRSTSTAGE, Races.DRAGONEWT, DragonoidRace.class);
    }

    @Override
    public void onGive(Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
    }
}