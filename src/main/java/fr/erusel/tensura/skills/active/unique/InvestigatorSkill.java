package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.inventorys.PlayerChooseGUI;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.FaustSkill;
import org.bukkit.entity.Player;

public class InvestigatorSkill extends Skill implements ActiveSkill {


    public InvestigatorSkill() {
        super("Investigator", "See the inventory of a Player", SkillTier.UNIQUE, 600, FaustSkill.class);
    }

    @Override
    
    public void onUse(Player player) {
        new PlayerChooseGUI(this).open(player);
    }
}
