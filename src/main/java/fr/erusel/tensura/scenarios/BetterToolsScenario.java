package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class BetterToolsScenario extends Scenario implements Eventable {

    public BetterToolsScenario() {
        super("Better Tools", "Every tool crafted get efficiency 3");
    }

    @Override
    public void onCraftItem(PrepareItemCraftEvent event) {
        ItemStack tool = event.getInventory().getResult();

        if (tool == null) return;

        if (tool.getType().toString().contains("PICKAXE") || tool.toString().contains("AXE") || tool.toString().contains("SHOVEL")) {
            tool.addEnchantment(Enchantment.DIG_SPEED, 3);
        }
    }
}
