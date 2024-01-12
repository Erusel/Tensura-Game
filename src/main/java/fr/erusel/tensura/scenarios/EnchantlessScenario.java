package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Material;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantlessScenario extends Scenario implements Eventable {
    public EnchantlessScenario() {
        super("Enchantless", "Dab");
    }

    @Override
    public void onPlayerEnchant(PrepareItemEnchantEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void onPlayerUseAnvil(PrepareAnvilEvent event) {

        ItemStack item = event.getInventory().getItem(1);

        if (item == null) {
            return;
        }

        if (!item.getType().equals(Material.ENCHANTED_BOOK)) {
            return;
        }
        event.setResult(null);
    }
}
