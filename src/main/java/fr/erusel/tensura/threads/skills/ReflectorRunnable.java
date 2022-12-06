package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class ReflectorRunnable extends BukkitRunnable {

    GPlayer gPlayer;

    public ReflectorRunnable(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void run() {
        gPlayer.setReflectorTime(gPlayer.getReflectorTime()-1);
        if (gPlayer.getReflectorTime()<=0){
            gPlayer.setReflector(false);
            this.cancel();
        }
    }
}
