package fr.erusel.tssdkuhc.threads;

import fr.erusel.tssdkuhc.objects.GPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class ImperceptibleRunnable extends BukkitRunnable {

    GPlayer gPlayer;

    public ImperceptibleRunnable(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void run() {
        gPlayer.setImperceptibleTime(gPlayer.getImperceptibleTime()-1);
        if (gPlayer.getImperceptibleTime()<=0){
            gPlayer.setImperceptible(false);
            this.cancel();
        }
    }
}
