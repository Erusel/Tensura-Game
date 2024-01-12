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

        if (tool == null) {
            return;
        }

        ItemMeta meta = tool.getItemMeta();

        if (meta == null) {
            return;
        }

        String toolName = tool.getType().toString();

        if (!isTool(toolName)) {
            return;
        }
        meta.setUnbreakable(true);
        tool.setItemMeta(meta);
    }

    private boolean isTool(String toolName) {
        return toolName.contains("PICKAXE")
                || toolName.contains("AXE")
                || toolName.contains("SHOVEL")
                || toolName.contains("HOE")
                || toolName.contains("SWORD");
    }

}
