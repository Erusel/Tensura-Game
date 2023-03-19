package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Collection;

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
        Player player = event.getPlayer();

        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onBlockBreak(event));

        gameManager.getGameModeInstance().onBlockBreak(event);
        playerManager.getGPlayerByUUID(player.getUniqueId()).getPlayerSkills().stream()
                .filter(skill -> skill instanceof Eventable)
                .forEach(skill -> ((Eventable) skill).onBlockBreak(event));

        Block block = event.getBlock();
        if (block.getType().equals(Material.DIAMOND_ORE) || event.getBlock().getType().equals(Material.GOLD_ORE)) {
            Collection<Entity> entities = block.getWorld().getNearbyEntities(block.getLocation(), 2, 2, 2);
            for (Entity entity : entities) {
                if (entity instanceof FallingBlock) {
                    entity.remove();
                }
            }
        }
    }
}
