package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class PlayerInteractionListener implements Listener {

    GameManager gameManager;
    ScoreBoardManager scoreBoardManager;
    PlayerManager playerManager;

    public PlayerInteractionListener(GameManager gameManager, ScoreBoardManager scoreBoardManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.scoreBoardManager = scoreBoardManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerMove(event);
            if (playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).isInHarvestFestival()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }

        gameManager.getGameModeInstance().onBlockBreak(event);
        for (Skill skill: playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill passiveSkill) passiveSkill.onBlockBreak(event);
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Bukkit.broadcastMessage("§8" + event.getPlayer().getName() + " §6> §7" + event.getMessage());
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onChat(event);
        }
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event){
        if (gameManager.getGameState().equals(GState.PLAYING)){
            Player player = event.getPlayer();
            gameManager.getGameModeInstance().onAdvancement(event);
            if (gameManager.getPlayerList().contains(player.getUniqueId())){
                int i = new Random().nextInt(100);
                if (i < 6){
                    player.sendMessage("§3You gain an skill");
                }
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)) event.setCancelled(true);
        if (!(event.getEntity() instanceof Player player)) return;
        if (gameManager.isRaceActivated()){
            if (playerManager.getGPlayerByUUID(player.getUniqueId()).getRace().getName().equals(Races.SLIME.getName())) event.setCancelled(true);

        }
    }
}
