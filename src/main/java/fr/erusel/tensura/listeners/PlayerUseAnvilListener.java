package fr.erusel.tensura.listeners;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;

public class PlayerUseAnvilListener implements Listener {

    private final GameManager gameManager;

    public PlayerUseAnvilListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerUseAnvil(PrepareAnvilEvent event) {

        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onPlayerUseAnvil(event));
    }
}
