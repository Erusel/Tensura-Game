package fr.erusel.tensura.threads;

import fr.erusel.tensura.enums.Teams;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CharybdisationRunnable extends BukkitRunnable {

    private final Teams teams;
    private final UUID victim;
    private final PlayerManager playerManager;
    private final GameManager gameManager;
    private int time = 0;

    public CharybdisationRunnable(Teams teams, UUID victim, PlayerManager playerManager, GameManager gameManager) {
        this.victim = victim;
        this.playerManager = playerManager;
        this.gameManager = gameManager;
        this.teams = teams;
    }

    @Override
    public void run() {

        if (time >= 10) {
            gameManager.finishGame(teams);
            this.cancel();
        }
        if (playerManager.getGPlayerByUUID(victim).isDead()){
            this.cancel();
            Bukkit.broadcastMessage("§cThe receptacle is dead");
        }

        Player player = Bukkit.getPlayer(victim);

        if (player == null) {
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 20, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 9999, false));

        time++;
    }

}
