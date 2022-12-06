package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.objects.GPlayer;
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
            this.cancel();
        }
    }
}