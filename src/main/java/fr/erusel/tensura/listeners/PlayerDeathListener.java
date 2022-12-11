package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class PlayerDeathListener implements Listener {

    GameManager gameManager;
    PlayerManager playerManager;

    public PlayerDeathListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (!gameManager.getGameState().equals(GState.PLAYING)){
            return;
        }

        Player player = event.getEntity();
        GPlayer gPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());

        if (event.getEntity().getKiller() != null){
            Player killer = event.getEntity().getKiller();
            GPlayer gKiller = playerManager.getGPlayerByUUID(killer.getUniqueId());
            gKiller.addKill(1);
            event.setDeathMessage("");
            gKiller.getPlayerSkills().stream()
                    .filter(skill -> skill instanceof Eventable)
                    .forEach(skill -> ((Eventable) skill).onPlayerKill(killer, player));
            if (gKiller.isGlutonnyActivated()){
                int i = new Random().nextInt(gPlayer.getPlayerSkills().size()-1);
                Skill skill = gPlayer.getPlayerSkills().get(i);
                gKiller.addSkill(skill);
                gPlayer.removeSkill(skill);
                gKiller.setGlutonny(false);
            }
        }

        gameManager.getGameModeInstance().onPlayerDeath(event);
        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onPlayerDeath(event));
        gPlayer.getPlayerSkills().stream()
                .filter(skill -> skill instanceof Eventable)
                .forEach(skill -> ((Eventable) skill).onPlayerDeath(event));

    }

}
