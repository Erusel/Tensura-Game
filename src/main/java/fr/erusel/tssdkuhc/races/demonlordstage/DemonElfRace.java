package fr.erusel.tssdkuhc.races.demonlordstage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DemonElfRace extends Race {


    public DemonElfRace() {
        super("Demon Elf", "DemonElf description", RaceStages.DEMONLORDSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
