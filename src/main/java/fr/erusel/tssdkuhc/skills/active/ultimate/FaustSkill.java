package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.inventorys.InvestigatorGUI;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class FaustSkill extends Skill implements ActiveSkill {


    public FaustSkill() {
        super("Faust, Lord of Investigation", "See the inventory of a Player", SkillTier.ULTIMATE, 900, null);
    }

    @Override
    
    public void onUse(Player player) {
        new InvestigatorGUI(this).open(player);
    }
}
