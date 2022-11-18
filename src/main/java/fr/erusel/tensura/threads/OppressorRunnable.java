package fr.erusel.tensura.threads;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class OppressorRunnable extends BukkitRunnable {

    GPlayer gPlayer;

    public OppressorRunnable(GPlayer gPlayer) {
        this.gPlayer = gPlayer;
    }

    @Override
    public void run() {
        gPlayer.setOppressorTime(gPlayer.getOppressorTime()-1);
        if (gPlayer.getOppressorTime()<=0){
            gPlayer.setOppressor(false);
            this.cancel();
        }
    }
}