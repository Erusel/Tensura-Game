package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.GameMode;
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
        Player player = event.getPlayer();
        GPlayer gPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerRespawn(event);
            player.setGameMode(GameMode.SPECTATOR);
            gameManager.addDeadPlayer(player.getUniqueId());
            gameManager.removeAlivePlayer(player.getUniqueId());
            if (gPlayer.canRessurect() && gPlayer.haveSkill(Skills.OSIRIS)){
                gPlayer.ressurect();
                gPlayer.setCanResurrect(false);
            }
        }
    }
}
