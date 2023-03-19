package fr.erusel.tensura.threads.skills;

import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class MineralFinderRunnable extends BukkitRunnable {

    FallingBlock block;
    int time;

    public MineralFinderRunnable(FallingBlock block, int time) {
        this.time = time;
        this.block = block;
    }

    @Override
    public void run() {
        if (time<=0){
            block.remove();
            this.cancel();
        }
        time--;
    }
}
