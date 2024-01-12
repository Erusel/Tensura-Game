package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class GappleCooldownScenario extends Scenario implements Eventable {

    // A CHANGER POUR TEMPS COOLDOWN PERSO

    public GappleCooldownScenario() {
        super("GappleCooldown", "Gapples have a cooldown of 2 minutes");
    }

    @Override
    public void onPlayerEat(PlayerItemConsumeEvent event) {

        Material item = event.getItem().getType();
        Player player = event.getPlayer();

        if (!item.equals(Material.GOLDEN_APPLE) && !item.equals(Material.ENCHANTED_GOLDEN_APPLE)) {
            return;
        }
        player.setCooldown(item, 2400);
    }
}
