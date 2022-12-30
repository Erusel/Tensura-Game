package fr.erusel.tensura.skills.passive.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HermesSkill extends Skill implements PassiveSkill {


    public HermesSkill() {
        super("Hermes, Lord of Haste", Skills.HERMES, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 0, null);
        super.addLore("Gain Haste III");
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 2));
    }

}
