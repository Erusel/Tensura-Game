package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.ImperceptibleRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HadesSkill extends Skill implements ActiveSkill {


    public HadesSkill() {
        super("Hades, Lord of Invisibility", "Grant you invisibility for 30sec and knockback near players", SkillTier.ULTIMATE, 1000, null);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0, false, true));
        for(Entity entity: player.getNearbyEntities(20,20,20)) {
            entity.setVelocity(entity.getLocation().getDirection().setY(0).normalize().multiply(3));
        }
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setImperceptible(true);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setImperceptibleTime(30);
        new ImperceptibleRunnable(Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()))
                .runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
