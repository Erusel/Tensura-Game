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


    @Override
    public void run() {
        if (GameManager.getInstance().getGameState().equals(GState.WAITING))
            ScoreBoardManager.getInstance().refreshWaitingScoreboard();
        if (GameManager.getInstance().getGameState().equals(GState.PLAYING)) {
            GameManager.getInstance().getGameModeInstance().refreshScoreboard();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (GameManager.getInstance().getPlayerList().contains(player.getUniqueId())) {
                    for (Skill skill : PlayerManager.getInstance().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
                        if (skill instanceof PassiveSkill) {
                            ((PassiveSkill) skill).eachSecond(player);
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
