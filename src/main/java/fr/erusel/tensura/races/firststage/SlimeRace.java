package fr.erusel.tensura.races.firststage;

import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.DemonSlimeRace;
import org.bukkit.entity.Player;

public class SlimeRace extends Race {


    public SlimeRace() {
        super("Slime", "Slime description", RaceStages.FIRSTSTAGE, Races.SLIME, DemonSlimeRace.class);
    }

    @Override
    public void onGive(Player player) {
        // No saturation loss
    }
}
