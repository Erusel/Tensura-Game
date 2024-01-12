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

        EntityDamageEvent.DamageCause cause = event.getCause();

        if (!cause.equals(EntityDamageEvent.DamageCause.FIRE) && !cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            return;
        }
        event.setCancelled(true);
    }
}
