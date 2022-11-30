package fr.erusel.tensura.threads;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameLoopRunnable extends BukkitRunnable {


    @Override
    public void run() {
        if (Main.getInstance().getGameManager().getGameState().equals(GState.WAITING))
            Main.getInstance().getScoreboardManager().refreshWaitingScoreboard();
        if (Main.getInstance().getGameManager().getGameState().equals(GState.PLAYING)) {
            Main.getInstance().getGameManager().getGameModeInstance().refreshScoreboard();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Main.getInstance().getGameManager().getPlayerList().contains(player.getUniqueId())) {
                    for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
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
