package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.entity.Player;

public class DeadlyHealScenario extends Scenario implements Eventable {

    public DeadlyHealScenario() {
        super("Deadly Heal", "Get heal when you kill a player");
    }

    @Override
    public void onPlayerKill(Player killer, Player deadPlayer) {
        killer.setHealth(killer.getHealth() + 6);
    }
}
