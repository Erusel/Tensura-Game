package fr.erusel.tensura.skills.passive.resistance;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;

public class InvestigatorResistantSkill extends Skill implements PassiveSkill {

    public InvestigatorResistantSkill() {
        super("Investigator Resistant", Skills.INVESTIGATOR, SkillScope.OBTAINABLE,  SkillTier.RESISTANCE, 0, null);
        super.addLore("Investigator can't see your stuff or skills");
    }
}
