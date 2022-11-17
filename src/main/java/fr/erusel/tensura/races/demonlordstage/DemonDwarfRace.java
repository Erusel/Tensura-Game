package fr.erusel.tensura.races.demonlordstage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import org.bukkit.entity.Player;

public class DemonDwarfRace extends Race {


    public DemonDwarfRace() {
        super("Demon Dwarf", "DemonDwarf description", RaceStages.DEMONLORDSTAGE, null);
    }

    @Override
    public void onGive(Player player) {

    }
}
