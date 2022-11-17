package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.inventorys.PlayerChooseGUI;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.active.ultimate.FaustSkill;
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
