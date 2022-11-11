package fr.erusel.tssdkuhc.skills.passive.resistance;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PoisonResistantSkill extends Skill implements PassiveSkill {

    public PoisonResistantSkill() { super("Poison-Resistant", "Cancel Poison Damage", SkillTier.RESISTANCE, 0, null);}

    @Override
    public void eachSecond(Player player) {
    }

    @Override
    public void onDeath(PlayerDeathEvent event) {
    }

    @Override
    public void onKill(Player killer, Player deadPlayer) {
    }

    @Override
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.POISON)) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
    }

}