package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class GappleCooldownScenario extends Scenario implements Eventable {

    // A CHANGER POUR TEMPS COOLDOWN PERSO

    public GappleCooldownScenario() {
        super("GappleCooldown", "Gapples have a cooldown of 2 minutes");
    }

    @Override
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item.getType().equals(Material.GOLDEN_APPLE) || item.getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
            player.setCooldown(item.getType(), 2400);
        }
    }
}
