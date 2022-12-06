package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.ImperceptibleRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HadesSkill extends Skill implements ActiveSkill {


    public HadesSkill() {
        super("Hades, Lord of Invisibility", "Grant you invisibility for 30sec and knockback near players", Skills.HADES, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1000, null);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0, false, true));
        for(Entity entity: player.getNearbyEntities(20,20,20)) {
            entity.setVelocity(entity.getLocation().getDirection().setY(0).normalize().multiply(3));
        }
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setImperceptible(true);
        new ImperceptibleRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 30)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }
}
