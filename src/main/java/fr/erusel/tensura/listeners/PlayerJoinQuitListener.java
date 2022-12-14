package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.GameSettingManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    private final GameManager gameManager;
    private final ScoreBoardManager scoreBoardManager;
    private final GameSettingManager gameSettingManager;
    private final PlayerManager playerManager;

    public PlayerJoinQuitListener(GameManager gameManager, ScoreBoardManager scoreBoardManager, GameSettingManager gameSettingManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.scoreBoardManager = scoreBoardManager;
        this.gameSettingManager = gameSettingManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        event.setJoinMessage("§7[§5+§7] " + player.getDisplayName());
        scoreBoardManager.initializeScoreboard(player);

        if (gameManager.getGameState().equals(GState.WAITING)){

            if (!(gameManager.getPlayerList().size() >= gameSettingManager.getMaxPlayer())){
                gameManager.getPlayerList().add(player.getUniqueId());
            }else {
                gameManager.addWaitingList(player.getUniqueId());
                player.sendMessage("§cToo many players, added in waiting list !");
            }
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            Utils.resetPlayer(player);
            scoreBoardManager.refreshWaitingScoreboard();
            return;
        }

        if (gameManager.getGameState().equals(GState.PLAYING)) {
            if (playerManager.getGPlayerByUUID(player.getUniqueId()) == null){
                playerManager.createPlayerGPlayer(player);
                player.setGameMode(GameMode.SPECTATOR);
            }
            if (playerManager.getGPlayerByUUID(player.getUniqueId()).haveLeaved()) {
                player.sendMessage("§cDue to your leave, you have been be kicked");
                player.setGameMode(GameMode.SPECTATOR);
            }
            if (playerManager.getGPlayerByUUID(player.getUniqueId()).getLeaveRunnable() != null){
                playerManager.getGPlayerByUUID(player.getUniqueId()).getLeaveRunnable().cancel();
                playerManager.getGPlayerByUUID(player.getUniqueId()).setLeaveRunnable(null);
            }

            gameManager.getGameModeInstance().refreshScoreboard();
            gameManager.getGameModeInstance().onPlayerJoin(event);
            gameManager.getActivatedScenariosInstance().stream()
                    .filter(s -> s instanceof Eventable)
                    .forEach(s -> ((Eventable) s).onPlayerJoin(event));
            return;
        }
        scoreBoardManager.refreshWaitingScoreboard();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§c-§7] " + player.getDisplayName());
        FastBoard board = scoreBoardManager.scoreboard.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }

        if (gameManager.getGameState().equals(GState.WAITING)){
            if (gameManager.getWaitingList().contains(player.getUniqueId())){
                gameManager.removeWaitingList(player.getUniqueId());
                return;
            }
            if (!gameManager.getPlayerList().contains(player.getUniqueId())){
                return;
            }
            if (gameManager.getWaitingList().size() > 0) {
                gameManager.getPlayerList().add(gameManager.getWaitingList().get(0));
                Bukkit.getPlayer(gameManager.getWaitingList().get(0)).sendMessage("§aA player leaved, you get is place !");
                gameManager.removeWaitingList(gameManager.getWaitingList().get(0));
            }

            gameManager.getPlayerList().remove(player.getUniqueId());
            return;
        }
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerLeave(event);
            gameManager.getActivatedScenariosInstance().stream()
                    .filter(s -> s instanceof Eventable)
                    .forEach(s -> ((Eventable) s).onPlayerLeave(event));
        }
    }


}
