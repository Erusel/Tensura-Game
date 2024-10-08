package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerSendChatListener implements Listener {

    private final GameManager gameManager;

    public PlayerSendChatListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        event.setFormat("§8" + event.getPlayer().getName() + " §6> §7" + event.getMessage());

        if (!gameManager.getGameState().equals(GState.PLAYING)){
            return;
        }
        gameManager.getGameModeInstance().onChat(event);
    }
}
