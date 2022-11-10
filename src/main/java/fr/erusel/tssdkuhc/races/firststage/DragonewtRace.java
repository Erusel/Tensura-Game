package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import org.bukkit.entity.Player;

public class DragonewtRace extends Race {


    public DragonewtRace() {
        super("Dragonewt", "Dragonewt description", RaceStages.FIRSTSTAGE);
    }

    @Override
    public void onGive(Player player) {

    }
}