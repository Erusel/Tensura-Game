package fr.erusel.tensura.skills.passive.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlashSkill extends Skill implements PassiveSkill {


    public FlashSkill() {
        super("Flash, Lord of Speed",  Skills.FLASH, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 0, null);
        super.addLore("Lore TODO");
    }

    @Override
    public void eachSecond(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2));
    }

}
