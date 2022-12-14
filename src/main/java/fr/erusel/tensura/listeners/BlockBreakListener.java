package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.utils.Utils;
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
    Random random = new Random();


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

        if (event.getBlock().getType().equals(Material.CHEST)){
            Block block = event.getBlock();
            Chest chest = (Chest) block.getState();

            if (gameManager.getCrateLocations().contains(block.getLocation())) {
                return;
            }

            gameManager.removeCrateLocation(block.getLocation());
            int randomLvl = random.nextInt(5);
            int randomAmountStuff = random.nextInt(15);

            ItemStack[] rareItems = {
                    new ItemBuilder(Material.TRIDENT).name("§ePoseidon's Fork").enchant(Enchantment.IMPALING, randomLvl).build(),
                    new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, randomLvl).build(),
                    new ItemBuilder(Material.STICK).enchant(Enchantment.KNOCKBACK, randomLvl).build(),
                    new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).build(),
                    new ItemBuilder(Material.CREEPER_SPAWN_EGG).build(),
            };

            for (int j = 0; j < 15; j++) {
                int randomSlot = random.nextInt(27);
                int chance = random.nextInt(100);
                if (chest.getInventory().getItem(randomSlot) != null){
                    continue;
                }

                if (chance <= 2) {
                    int randomRareItem = random.nextInt(rareItems.length);
                    chest.getInventory().setItem(randomSlot, rareItems[randomRareItem]);
                    continue;
                }
                int randomItem = new Random().nextInt(Utils.items.length);
                ItemStack itemStack = Utils.items[randomItem];
                itemStack.setAmount(randomAmountStuff);
                chest.getInventory().setItem(randomSlot, itemStack);

            }

        }

        gameManager.getGameModeInstance().onBlockBreak(event);
        playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills().stream()
                .filter(skill -> skill instanceof Eventable)
                .forEach(skill -> ((Eventable) skill).onBlockBreak(event));
    }
}
