package fr.erusel.tensura.threads;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameLoopRunnable extends BukkitRunnable {

    GameManager gameManager;
    PlayerManager playerManager;
    ScoreBoardManager scoreBoardManager;

    public GameLoopRunnable(GameManager gameManager, PlayerManager playerManager, ScoreBoardManager scoreBoardManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
        this.scoreBoardManager = scoreBoardManager;
    }

    @Override
    public void run() {
        if (gameManager.getGameState().equals(GState.WAITING))
           scoreBoardManager.refreshWaitingScoreboard();
        if (gameManager.getGameState().equals(GState.PLAYING)) {
            gameManager.getGameModeInstance().refreshScoreboard();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (gameManager.getPlayerList().contains(player.getUniqueId())) {
                    for (Skill skill : playerManager.getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
                        if (skill instanceof PassiveSkill passiveSkill) {
                            passiveSkill.eachSecond(player);
                        } else if (skill instanceof ActiveSkill) {
                            if (skill.inCooldown()) {
                                skill.setCurrentCooldown(skill.getCurrentCooldown() - 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
