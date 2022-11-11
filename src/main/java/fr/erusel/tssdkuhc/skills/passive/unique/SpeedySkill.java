package fr.erusel.tssdkuhc.skills.passive.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.passive.ultimate.FlashSkill;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedySkill extends Skill implements PassiveSkill {


    public SpeedySkill() {
        super("Speedy", "Give you speed 2", SkillTier.UNIQUE, 0, FlashSkill.class);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
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

    }

}
