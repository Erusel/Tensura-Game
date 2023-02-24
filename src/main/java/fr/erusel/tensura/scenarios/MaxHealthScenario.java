package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class MaxHealthScenario extends Scenario implements Eventable {

    // CHANGER RACE ORC FONCTIONNEMENT
    // A CHANGER POUR VIE MAX PERSO

    public MaxHealthScenario() {
        super("MaxHealth", "Max health is 20 hearts");
    }

    @Override
    public void onStart() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
            player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(10, 10));
        }
    }

}
