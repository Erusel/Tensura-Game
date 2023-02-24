package fr.erusel.tensura.listeners;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishingListener implements Listener {

    GameManager gameManager;

    public PlayerFishingListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {

        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onPlayerFish(event));
    }
}
