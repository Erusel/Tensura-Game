package fr.erusel.tensura.skills.passive.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.passive.ultimate.HecateSkill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GuardianSkill extends Skill implements PassiveSkill {

    public GuardianSkill() {
        super("Guardian", "Grant you resistance", Skills.GUARDIAN, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 0, HecateSkill.class);
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0));
    }


}
