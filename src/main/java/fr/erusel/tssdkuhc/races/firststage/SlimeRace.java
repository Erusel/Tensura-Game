package fr.erusel.tssdkuhc.races.firststage;

import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.races.demonlordstage.DemonSlimeRace;
import org.bukkit.entity.Player;

public class SlimeRace extends Race {


    public SlimeRace() {
        super("Slime", "Slime description", RaceStages.FIRSTSTAGE, DemonSlimeRace.class);
    }

    @Override
    public void onGive(Player player) {

    }
}
