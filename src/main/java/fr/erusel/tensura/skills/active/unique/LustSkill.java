package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.skills.LustGUI;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class LustSkill extends Skill implements ActiveSkill {

    public LustSkill() {
        super("Lust", "Resurrect any dead players", Skills.LUST, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 2000, Skills.OSIRIS);
    }

    @Override
    public void onUse(Player player) {
        new LustGUI(this, player).open(player);
    }
}
