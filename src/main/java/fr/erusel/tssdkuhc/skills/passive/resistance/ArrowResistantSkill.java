package fr.erusel.tssdkuhc.skills.passive.resistance;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowResistantSkill extends Skill implements PassiveSkill {

    public ArrowResistantSkill() { super("Arrow Resistant", "Cancel Arrow Damage", SkillTier.RESISTANCE, 0, null);}

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

    }

    @Override
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow){
            event.setCancelled(true);
        }
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
    }

}