package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UltraSpeedRegenSkill extends Skill implements ActiveSkill {


    public UltraSpeedRegenSkill() {
        super("Ultra Speed Regen", "Grant you regeneration 2 for 10sec", Skills.ULTRASPEEDREGEN, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 1500, null);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 3));
        activateCooldown();
    }
}