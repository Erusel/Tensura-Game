package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DragonoidRace;
import org.bukkit.entity.Player;

public class DragonewtRace extends Race {


    public DragonewtRace() {
        super("Dragonewt", "Dragonewt description", RaceStages.FIRSTSTAGE, DragonoidRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}