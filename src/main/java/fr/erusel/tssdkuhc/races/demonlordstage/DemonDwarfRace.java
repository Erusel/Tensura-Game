package fr.erusel.tssdkuhc.races.demonlordstage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DemonDwarfRace extends Race {


    public DemonDwarfRace() {
        super("Demon Dwarf", "DemonDwarf description", RaceStages.DEMONLORDSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
