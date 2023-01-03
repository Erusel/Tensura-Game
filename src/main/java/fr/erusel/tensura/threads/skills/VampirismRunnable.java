package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class VampirismRunnable extends BukkitRunnable {
    GPlayer gPlayer;
    int time;

    public VampirismRunnable(GPlayer gPlayer, int time) {
        this.gPlayer = gPlayer;
        this.time = time;
    }

    @Override
    public void run() {
        if (time<=0){
            gPlayer.setVampirism(false);
            this.cancel();
        }
        time--;
    }
}

