package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class GravityManipulationRunnable extends BukkitRunnable {

    GPlayer gPlayer;

    public GravityManipulationRunnable(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void run() {
        gPlayer.setGravityTime(gPlayer.getGravityTime()-1);
        if (gPlayer.getGravityTime()<=0){
            gPlayer.setGravity(false);
            this.cancel();
        }
    }
}