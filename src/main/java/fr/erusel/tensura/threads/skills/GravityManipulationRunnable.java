package fr.erusel.tensura.threads.skills;

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
        if (time<=0){
            gPlayer.setGravity(false);
            Player player = Bukkit.getPlayer(gPlayer.getUUID());
            player.getAllowFlight();
            player.setAllowFlight(false);
            player.setFlying(false);
            this.cancel();
        }
        time--;
    }
}