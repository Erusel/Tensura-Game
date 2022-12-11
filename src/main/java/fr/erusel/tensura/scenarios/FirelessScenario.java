package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.event.entity.EntityDamageEvent;

public class FirelessScenario extends Scenario implements Eventable {
    public FirelessScenario() {
        super("Fireless", "Lore");
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            event.setCancelled(true);
        }
    }
}
