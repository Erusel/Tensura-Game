package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockBreakListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    public BlockBreakListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }

        if (event.getBlock().getType().equals(Material.CHEST) && gameManager.getGameState().equals(GState.PLAYING)){
            Block block = event.getBlock();
            if (gameManager.getCrateLocations().contains(block.getLocation())) {
                gameManager.removeCrateLocation(block.getLocation());
                int randomLvl = new Random().nextInt(5);
                int randomAmountStuff = new Random().nextInt(15);
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

                for (int j = 0; j < 15; j++) {
                    int randomSlot = new Random().nextInt(27);
                    if (chest.getInventory().getItem(randomSlot) == null){
                        int rng = new Random().nextInt(100);
                        if (rng <= 2){
                            int randomRareItem = new Random().nextInt(rareItems.length);
                            chest.getInventory().setItem(randomSlot, rareItems[randomRareItem]);
                        } else {
                            int randomItem = new Random().nextInt(items.length);
                            chest.getInventory().setItem(randomSlot, items[randomItem]);
                        }
                    }
                }
            }
        }

        gameManager.getGameModeInstance().onBlockBreak(event);
        playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills().stream()
                .filter(skill -> skill instanceof Eventable)
                .forEach(skill -> ((Eventable) skill).onBlockBreak(event));
    }
}
