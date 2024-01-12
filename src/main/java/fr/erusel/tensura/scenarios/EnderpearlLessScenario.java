package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EnderpearlLessScenario extends Scenario implements Eventable {

    public EnderpearlLessScenario() {
        super("EnderpearlLess", "Enderpearls can't be used");
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {

        ItemStack item = event.getItem();
        Action action = event.getAction();

        if (!action.equals(Action.RIGHT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (item == null) {
            return;
        }
        if (!item.getType().equals(Material.ENDER_PEARL)) {
            return;
        }
        event.setCancelled(true);
    }
}
