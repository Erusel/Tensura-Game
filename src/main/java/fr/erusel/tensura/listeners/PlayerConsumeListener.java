package fr.erusel.tensura.listeners;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerConsumeListener implements Listener {

    private final GameManager gameManager;

    public PlayerConsumeListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {

        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onPlayerEat(event));
    }
}
