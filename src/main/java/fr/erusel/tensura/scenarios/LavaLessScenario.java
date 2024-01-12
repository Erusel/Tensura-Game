package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.event.entity.EntityDamageEvent;

public class LavaLessScenario extends Scenario implements Eventable {
    public LavaLessScenario() {
        super("Lavaless", "Lore");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {

        if (!event.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
            return;
        }
        event.setCancelled(true);
    }
}
