package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitTask;

public class SneakLessScenario extends Scenario implements Eventable {

    private BukkitTask sneakTask = null;

    public SneakLessScenario() {
        super("SneakLess", "Sneaking player receive damage");
    }

    @Override
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!event.isSneaking()) {
            return;
        }
        sneakTask = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            if (player.isSneaking()) {
                player.damage(1);
            } else {
                sneakTask.cancel();
            }
        }, 0, 40);
    }
}
