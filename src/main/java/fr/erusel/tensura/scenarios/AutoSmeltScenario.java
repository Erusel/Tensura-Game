package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AutoSmeltScenario extends Scenario implements Eventable {

    public AutoSmeltScenario() {
        super("Auto Smelt", "Auto smelt every mined ore.");
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType().toString().contains("ORE")) {
            if (block.getType().equals(Material.IRON_ORE)) {
                event.setDropItems(false);
                for (ItemStack item: block.getDrops()) {
                    item.setType(Material.IRON_INGOT);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                }
                return;
            }
            if (block.getType().equals(Material.GOLD_ORE)) {
                event.setDropItems(false);
                for (ItemStack item: block.getDrops()) {
                    item.setType(Material.GOLD_INGOT);
                    block.getWorld().dropItemNaturally(block.getLocation(), item);
                }
            }
        }
    }
}
