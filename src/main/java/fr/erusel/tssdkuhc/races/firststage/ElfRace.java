package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class ElfRace extends Race {


    public ElfRace() {
        super("Elf", "Elf description", RaceStages.FIRSTSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}
