package fr.erusel.tensura.skills.passive.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.passive.ultimate.FlashSkill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedySkill extends Skill implements PassiveSkill {


    public SpeedySkill() {
        super("Speedy", "Grant you speed 2", Skills.SPEEDY, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 0, FlashSkill.class);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
    }


}
