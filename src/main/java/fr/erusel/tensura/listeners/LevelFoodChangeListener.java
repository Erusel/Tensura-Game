package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.GameSettingManager;
import fr.erusel.tensura.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class LevelFoodChangeListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private final GameSettingManager gameSettingManager;

    public LevelFoodChangeListener(GameManager gameManager, PlayerManager playerManager, GameSettingManager gameSettingManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
        this.gameSettingManager = gameSettingManager;
    }
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
        }
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (gameSettingManager.isRaceActivated()){
            if (playerManager.getGPlayerByUUID(player.getUniqueId()).isRace(Races.SLIME)) {
                event.setCancelled(true);
            }

        }
    }
}
