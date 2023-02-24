package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BreaklessScenario extends Scenario implements Eventable {

    public BreaklessScenario() {
        super("Breakless", "Crafted items can't be broken");
    }

    @Override
    public void onCraftItem(PrepareItemCraftEvent event) {
        ItemStack tool = event.getInventory().getResult();

        if (tool == null) return;
        ItemMeta meta = tool.getItemMeta();
        if (meta == null) return;

        if (tool.getType().toString().contains("PICKAXE") || tool.getType().toString().contains("AXE") || tool.getType().toString().contains("SHOVEL") || tool.getType().toString().contains("HOE") || tool.getType().toString().contains("SWORD")) {
            meta.setUnbreakable(true);
            tool.setItemMeta(meta);
        }
    }

}
