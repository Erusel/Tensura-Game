package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    public PlayerMoveListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            return;
        }
        gameManager.getGameModeInstance().onPlayerMove(event);
        if (playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).isInHarvestFestival()) {
            event.setCancelled(true);
        }
    }

}
