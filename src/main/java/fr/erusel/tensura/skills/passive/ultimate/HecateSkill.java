package fr.erusel.tensura.skills.passive.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HecateSkill extends Skill implements PassiveSkill {


    public HecateSkill() {
        super("Hecate, Lord of Protection", Skills.HECATE, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 900, null);
        super.addLore("Gain Resistance II");
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 1));
    }
}

