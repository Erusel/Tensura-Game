package fr.erusel.tensura.skills.passive.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BerserkerSkill extends Skill implements PassiveSkill, Eventable {


    public BerserkerSkill() {
        super("Berserker", Skills.BERSERKER, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 0, Skills.MOGIS);
        super.addLore("Gain Strength I for 20 seconds if you killed a player");
    }

    @Override
    public void onPlayerKill(Player killer, Player deadPlayer) {
        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
    }


}
