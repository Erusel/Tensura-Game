package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    GameManager gameManager;
    ScoreBoardManager scoreBoardManager;
    PlayerManager playerManager;

    public PlayerJoinQuitListener(GameManager gameManager, ScoreBoardManager scoreBoardManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.scoreBoardManager = scoreBoardManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        event.setJoinMessage("§7[§5+§7] " + player.getDisplayName());
        scoreBoardManager.initializeScoreboard(player);

        if (gameManager.getGameState().equals(GState.WAITING)){
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            Utils.resetPlayer(player);
            scoreBoardManager.refreshWaitingScoreboard();
            if (!(gameManager.getPlayerList().size() >= gameManager.getMaxPlayer())){
                gameManager.getPlayerList().add(player.getUniqueId());
            }else {
                gameManager.addWaitingList(player.getUniqueId());
                player.sendMessage("§cToo many players, added in waiting list !");
            }
        }
        if (gameManager.getGameState().equals(GState.PLAYING)) {
            gameManager.getGameModeInstance().refreshScoreboard();
            gameManager.getGameModeInstance().onPlayerJoin(event);
            for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onPlayerJoin(event);
        } else {
            scoreBoardManager.refreshWaitingScoreboard();
        }
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
            if (gameManager.getPlayerList().contains(player.getUniqueId())){
                gameManager.getPlayerList().remove(player.getUniqueId());
                if (gameManager.getWaitingList().size() > 0){
                    gameManager.getPlayerList().add(gameManager.getWaitingList().get(0));
                    Bukkit.getPlayer(gameManager.getWaitingList().get(0)).sendMessage("§aA player leaved, you get is place !");
                    gameManager.removeWaitingList(gameManager.getWaitingList().get(0));
                }
            }else if (gameManager.getWaitingList().contains(player.getUniqueId())) gameManager.removeWaitingList(player.getUniqueId());
        }
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerLeave(event);
            for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onPlayerLeave(event);

        }
    }


}
