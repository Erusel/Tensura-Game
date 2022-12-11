package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GravityManipulationRunnable extends BukkitRunnable {

    GPlayer gPlayer;
    int time;

    public GravityManipulationRunnable(GPlayer gPlayer, int time) {
        this.gPlayer = gPlayer;
        this.time = time;
    }

    @Override
    public void run() {
        time--;
        if (time<=0){
            gPlayer.setGravity(false);
            //set flying to false for the player
            Player player = Bukkit.getPlayer(gPlayer.getUUID());
            player.getAllowFlight();
            player.setAllowFlight(false);
            player.setFlying(false);
            this.cancel();
        }
    }
}