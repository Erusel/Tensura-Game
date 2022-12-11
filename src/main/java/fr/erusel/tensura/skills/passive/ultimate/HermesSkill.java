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
        super("Hermes, Lord of Haste", "Grant you haste 3", Skills.HERMES, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 0, null);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 2));
    }

}
