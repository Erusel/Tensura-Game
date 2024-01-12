package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.OppressorRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OppressorSkill extends Skill implements ActiveSkill, Eventable {


    public OppressorSkill() {
        super("Oppressor", Skills.OPPRESSOR, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 800, Skills.JANUS);
        super.addLore("Knockback away entities for 30 seconds");
    }

    @Override
    public void onUse(Player player) {

        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setOppressor(true);
        new OppressorRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 30)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player damager)){
            return;
        }
        if (!getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).isOppressorActivated()){
            return;
        }
        event.getEntity().setVelocity(damager.getLocation().getDirection().setY(0).normalize().multiply(2));
    }
}
