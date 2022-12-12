package fr.erusel.tensura.listeners;

import fr.erusel.tensura.managers.GameManager;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerInteractionListener implements Listener {

    GameManager gameManager;
    Random random = new Random();

    public PlayerInteractionListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerInteractListener(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.CHEST)) {
                if (gameManager.getCrateLocations().contains(block.getLocation())) {
                    gameManager.removeCrateLocation(block.getLocation());
                    int randomLvl = random.nextInt(5);
                    int randomAmountStuff = random.nextInt(15);
                    Chest chest = (Chest) block.getState();
                    ItemStack[] rareItems = {
                            new ItemBuilder(Material.TRIDENT).name("§ePoseidon's Fork").enchant(Enchantment.IMPALING, randomLvl).build(),
                            new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, randomLvl).build(),
                            new ItemBuilder(Material.STICK).enchant(Enchantment.KNOCKBACK, randomLvl).build(),
                            new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).build(),
                            new ItemBuilder(Material.CREEPER_SPAWN_EGG).build(),
                    };

                    ItemStack[] items = {
                            new ItemBuilder(Material.DIAMOND).build(),
                            new ItemBuilder(Material.IRON_INGOT).build(),
                            new ItemBuilder(Material.GOLD_INGOT).build(),
                            new ItemBuilder(Material.GOLDEN_APPLE).build(),
                            new ItemBuilder(Material.ENDER_PEARL).build(),
                            new ItemBuilder(Material.BOW).build(),
                            new ItemBuilder(Material.ARROW).amount(randomAmountStuff).build(),
                            new ItemBuilder(Material.IRON_HELMET).build(),
                            new ItemBuilder(Material.IRON_CHESTPLATE).build(),
                            new ItemBuilder(Material.IRON_LEGGINGS).build(),
                            new ItemBuilder(Material.IRON_BOOTS).build(),
                            new ItemBuilder(Material.FURNACE_MINECART).name("§elol useless").build(),
                            new ItemBuilder(Material.WATER_BUCKET).build(),
                            new ItemBuilder(Material.LAVA_BUCKET).build(),
                            new ItemBuilder(Material.CAKE).name("§eThe lie").build(),
                    };
                    for (int j = 0; j < randomAmountStuff; j++) {
                        int randomSlot = random.nextInt(27);
                        if (chest.getInventory().getItem(randomSlot) == null){
                            int rng = random.nextInt(100);
                            if (rng <= 2){
                                int randomRareItem = random.nextInt(rareItems.length);
                                chest.getInventory().setItem(randomSlot, rareItems[randomRareItem]);
                            } else {
                                int randomItem = random.nextInt(items.length);
                                chest.getInventory().setItem(randomSlot, items[randomItem]);
                            }
                        }
                    }
                }
            }
        }
    }
}
