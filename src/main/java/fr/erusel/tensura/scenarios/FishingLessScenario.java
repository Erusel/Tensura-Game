package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingLessScenario extends Scenario implements Eventable {

    public FishingLessScenario() {
        super("FishingLess", "Fishing is disabled");
    }

    @Override
    public void onPlayerFish(PlayerFishEvent event) {
        event.setCancelled(true);
    }
}
