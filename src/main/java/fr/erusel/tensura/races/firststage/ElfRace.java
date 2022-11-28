package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonElfRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class ElfRace extends Race {


    public ElfRace() {
        super("Elf", "Elf description", RaceStages.FIRSTSTAGE, DemonElfRace.class);
    }

    @Override
    public void onGive(Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
    }
}
