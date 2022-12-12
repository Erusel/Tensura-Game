package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class OppressorRunnable extends BukkitRunnable {

    GPlayer gPlayer;
    int time;

    public OppressorRunnable(GPlayer gPlayer, int time) {
        this.gPlayer = gPlayer;
        this.time = time;
    }

    @Override
    public void run() {
        if (time<=0){
            gPlayer.setOppressor(false);
            this.cancel();
        }
        time--;
    }
}
