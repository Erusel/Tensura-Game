package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonElfRace;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class ElfRace extends Race {


    public ElfRace() {
        super("Elf", "Elf description", RaceStages.FIRSTSTAGE, Races.ELF, DemonElfRace.class);
    }

    @Override
    public void onGive(Player player) {
        // Faster
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.9);
    }
}
