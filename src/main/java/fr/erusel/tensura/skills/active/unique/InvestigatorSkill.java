package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.PlayerChooseGUI;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class InvestigatorSkill extends Skill implements ActiveSkill {


    public InvestigatorSkill() {
        super("Investigator", "See the inventory of a Player", Skills.INVESTIGATOR, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 600, Skills.FAUST);
    }

    @Override
    
    public void onUse(Player player) {
        new PlayerChooseGUI(this).open(player);
    }
}
