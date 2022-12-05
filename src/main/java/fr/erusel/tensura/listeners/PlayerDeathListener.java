package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Random;

public class PlayerDeathListener implements Listener {

    GameManager gameManager;
    ScoreBoardManager scoreBoardManager;
    PlayerManager playerManager;

    public PlayerDeathListener(GameManager gameManager, ScoreBoardManager scoreBoardManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.scoreBoardManager = scoreBoardManager;
        this.playerManager = playerManager;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        GPlayer gKiller = playerManager.getGPlayerByUUID(killer.getUniqueId());
        GPlayer gPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
        gKiller.addKill(1);
        event.setDeathMessage("");
        Utils.VoiceOfTheWorldBroadcast("Player §c" + player.getName() + " §3died against §c" + killer.getName());

        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerDeath(event);
            for (Skill skill : gKiller.getPlayerSkills()) if (skill instanceof PassiveSkill passiveSkill) passiveSkill.onKill(killer, player);
            for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onPlayerDeath(event);
            if (gKiller.isGlutonnyActivated()){
                int i = new Random().nextInt(gPlayer.getPlayerSkills().size()-1);
                Skill skill = gPlayer.getPlayerSkills().get(i);
                gKiller.addSkill(skill);
                gPlayer.removeSkill(skill);
                gKiller.setGlutonny(false);
            }
        }

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
