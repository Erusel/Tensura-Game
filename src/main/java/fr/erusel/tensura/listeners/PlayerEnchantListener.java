package fr.erusel.tensura.listeners;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class PlayerEnchantListener implements Listener {

    private final GameManager gameManager;

    public PlayerEnchantListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerEnchant(PrepareItemEnchantEvent event) {

        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onPlayerEnchant(event));
    }
}
