package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    public PlayerRespawnListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            return;
        }
        Player player = event.getPlayer();
        GPlayer gPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
        gameManager.getGameModeInstance().onPlayerRespawn(event);

    }
}
