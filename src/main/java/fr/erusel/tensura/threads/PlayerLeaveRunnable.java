package fr.erusel.tensura.threads;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerLeaveRunnable extends BukkitRunnable {

    GameManager gameManager;
    Player player;
    GPlayer gPlayer;

    public PlayerLeaveRunnable(Player player, GameManager gameManager, GPlayer gPlayer) {
        this.gPlayer = gPlayer;
        this.player = player;
        this.gameManager = gameManager;
    }

    int time = 5; //300

    @Override
    public void run() {

        if (time == 0) {
            gameManager.getPlayerList().remove(gPlayer.getUUID());
            gameManager.removeAlivePlayer(player.getUniqueId());
            gameManager.addDeadPlayer(player.getUniqueId());
            gPlayer.setLeaved(true);
            Bukkit.broadcastMessage("§cDue to the left of a player, he was kicked of the game !");
            this.cancel();
        }
        time--;
    }
}
