package fr.erusel.tssdkuhc.skills.passive.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.passive.ultimate.FlashSkill;
import fr.erusel.tssdkuhc.skills.passive.ultimate.HecateSkill;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GuardianSkill extends Skill implements PassiveSkill {

    public GuardianSkill() {
        super("Guardian", "Grant you resistance", SkillTier.UNIQUE, 0, HecateSkill.class);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0));
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

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
    }

}
