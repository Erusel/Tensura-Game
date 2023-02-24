package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.enums.Scenarios;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class StartingKitScenario extends Scenario implements Eventable {

    private final ItemStack[] kit = {
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.IRON_PICKAXE),
            new ItemStack(Material.IRON_AXE),
            new ItemStack(Material.IRON_SHOVEL),
    };

    public StartingKitScenario() {
        super("StartingKit", "Give a starting kit to players");
    }

    @Override
    public void onStart() {
        for (UUID uuid : getGameManager().getPlayerList()) {
            Player player = Bukkit.getPlayer(uuid);
            for (ItemStack item : kit) {
                player.getInventory().addItem(item);
                if (!item.getType().equals(Material.IRON_SWORD)){
                    if (getGameManager().getActivatedScenarios().contains(Scenarios.BETTERTOOLS)) {
                        item.addEnchantment(Enchantment.DIG_SPEED, 3);
                    }
                }
            }
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
        }
    }
}
