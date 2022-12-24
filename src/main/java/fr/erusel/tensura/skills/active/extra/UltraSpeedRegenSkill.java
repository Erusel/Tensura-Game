package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class UltraSpeedRegenSkill extends Skill implements ExtraSkill {


    public UltraSpeedRegenSkill() {
        super("Ultra Speed Regen",  Skills.ULTRASPEEDREGEN, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 1500, null);
        super.addLore("Lore TODO");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Fast regeneration";
    }

    @Override
    public void onRightClick(Player player) {
        player.sendMessage("Pas fé, fot le ferre");
        activateCooldown();
    }

    @Override
    public void onLeftClick(Player player) {

    }
}