package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightvisionScenario extends Scenario implements Eventable {

    public NightvisionScenario() {
        super("Nightvision", "Grant nightvision for everyone");
    }

    @Override
    public void onStart() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
        }
    }
}
