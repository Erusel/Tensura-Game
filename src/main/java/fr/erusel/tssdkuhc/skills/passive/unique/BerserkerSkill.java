package fr.erusel.tssdkuhc.skills.passive.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BerserkerSkill extends Skill implements PassiveSkill {


    public BerserkerSkill() {
        super("Berserker", "Each kill give you Strength", SkillTier.UNIQUE, 0);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0));
    }

    @Override
    public void onDeath(PlayerDeathEvent event) {

    }

    @Override
    public void onKill(Player killer, Player deadPlayer) {
        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
    }
}
