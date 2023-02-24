package fr.erusel.tensura.skills.passive.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class TelekinesisSkill extends Skill implements ExtraSkill, PassiveSkill {


    public TelekinesisSkill() {
        super("Telekinesis", Skills.TELEKINESIS, SkillScope.OBTAINABLE, SkillTier.EXTRA, 0, null);
    }

    @Override
    public String getRightClickSkillLore() {
        return "None";
    }
    @Override
    public String getLeftClickSkillLore() {
        return "None";
    }
    @Override
    public void onRightClick(Player player) {
    }
    @Override
    public void onLeftClick(Player player) {
    }
}
