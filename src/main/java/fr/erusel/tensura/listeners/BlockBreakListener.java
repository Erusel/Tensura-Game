package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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

        gameManager.getGameModeInstance().onBlockBreak(event);
        playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills().stream()
                .filter(skill -> skill instanceof Eventable)
                .forEach(skill -> ((Eventable) skill).onBlockBreak(event));
    }
}
