package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.Random;

public class PlayerAdvancementDoneListener implements Listener {

    private final GameManager gameManager;
    private final Random random = new Random();

    public PlayerAdvancementDoneListener(GameManager gameManager) {
        this.gameManager = gameManager;

    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            return;
        }
        if (!event.getAdvancement().getKey().getKey().contains("recipes/")) {
            return;
        }

        Player player = event.getPlayer();
        gameManager.getGameModeInstance().onAdvancement(event);

        if (gameManager.getPlayerList().contains(player.getUniqueId())) {
            return;
        }
        if (random.nextInt(100) < 6) {
            player.sendMessage("§3You gain an skill");
        }

    }

}
