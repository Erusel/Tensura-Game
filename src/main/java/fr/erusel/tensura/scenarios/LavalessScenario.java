package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.event.entity.EntityDamageEvent;

public class LavalessScenario extends Scenario implements Eventable {
    public LavalessScenario() {
        super("Lavaless", "Lore");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
            event.setCancelled(true);
        }
    }
}
