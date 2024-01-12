package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.ReflectorRunnable;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ReflectorSkill extends Skill implements ActiveSkill, Eventable {


    public ReflectorSkill() {
        super("Reflector", Skills.REFLECTOR, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, Skills.OMOIKANE);
        super.addLore("Return damage to the attacker for 10 seconds");
    }

    @Override
    public void onUse(Player player) {
        GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gPlayer.setReflector(true);
        new ReflectorRunnable(gPlayer, 10)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Arrow projectile)) {
            return;
        }
        if (!(event.getEntity() instanceof Player damaged)){
            return;
        }

        LivingEntity shooter = (LivingEntity) projectile.getShooter();

        if (shooter == null) {
            return;
        }

        if (!getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).isReflectorActivated()) {
            return;
        }
        shooter.damage(event.getDamage());
        event.setCancelled(true);
    }
}
