package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonDwarfRace;
import org.bukkit.entity.Player;

public class DwarfRace extends Race {


    public DwarfRace() {
        super("Dwarf", "Dwarf description", RaceStages.FIRSTSTAGE, DemonDwarfRace.class);
    }

    @Override
    public void onGive(Player player) {
        // 20% damage resistance
    }
}
