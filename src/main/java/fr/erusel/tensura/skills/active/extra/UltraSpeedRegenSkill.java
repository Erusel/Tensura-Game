package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UltraSpeedRegenSkill extends Skill implements ExtraSkill {

    public UltraSpeedRegenSkill() {
        super("Health Manipulation", Skills.ULTRASPEEDREGEN, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 1500, null);
        super.addLore("Use the power of Life");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Give you regeneration IV for 7 seconds";
    }

    @Override
    public void onRightClick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 140, 3));
        activateCooldown();
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Give you health boost II for 2 minutes";
    }

    @Override
    public void onLeftClick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 1));
        activateCooldown();
    }
}