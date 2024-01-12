package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DiamondLessScenario extends Scenario implements Eventable {

    public DiamondLessScenario() {
        super("DiamondLess", "Diamond ores don't drop diamonds but 2 gold instead");
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getBlock().getType().toString().contains("DIAMOND_ORE")) {
            return;
        }

        Location location = event.getBlock().getLocation();
        ItemStack itemsToDrop = new ItemStack(Material.GOLD_INGOT, 2);

        event.setDropItems(false);
        event.getBlock().getWorld().dropItemNaturally(location, itemsToDrop);
    }
}
