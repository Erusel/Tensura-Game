package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DwarfRace extends Race {


    public DwarfRace() {
        super("Dwarf", "Dwarf description", RaceStages.FIRSTSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
