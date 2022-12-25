package fr.erusel.tensura.threads;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameLoopRunnable extends BukkitRunnable {

    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private final ScoreBoardManager scoreBoardManager;

    public GameLoopRunnable(GameManager gameManager, PlayerManager playerManager, ScoreBoardManager scoreBoardManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
        this.scoreBoardManager = scoreBoardManager;
    }

    @Override
    public void run() {
        if (gameManager.getGameState().equals(GState.WAITING)) {
            scoreBoardManager.refreshWaitingScoreboard();
            return;
        }

        if (gameManager.getGameState().equals(GState.PLAYING)) {
            gameManager.getGameModeInstance().refreshScoreboard();
            Bukkit.getOnlinePlayers().stream()
                    .filter(p -> gameManager.getPlayerList().contains(p.getUniqueId()))
                    .forEach(p -> playerManager.getGPlayerByUUID(p.getUniqueId()).getPlayerSkills().stream()
                            .filter(skill -> skill instanceof PassiveSkill)
                            .forEach(skill -> ((PassiveSkill) skill).eachSecond(p)));
            Bukkit.getOnlinePlayers().stream()
                    .filter(p -> gameManager.getPlayerList().contains(p.getUniqueId()))
                    .forEach(p -> playerManager.getGPlayerByUUID(p.getUniqueId()).getPlayerSkills().stream()
                            .filter(Skill::inCooldown)
                            .forEach(skill -> skill.setCurrentCooldown(skill.getCurrentCooldown() - 1)));
        }
    }
}
