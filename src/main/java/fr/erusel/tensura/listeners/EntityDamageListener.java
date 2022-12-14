package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    public EntityDamageListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }


        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onEntityDamage(event));

        playerManager.getGPlayerByUUID(player.getUniqueId()).getPlayerSkills().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable)s).onEntityDamage(event));
    }


}
