package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FireResistantSkill extends Skill implements PassiveSkill {

    public FireResistantSkill() { super("Fire Resistant", "Cancel Fire Damage", SkillTier.RESISTANCE, 0, null);}

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
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent event) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
    }

}