package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.inventorys.InvestigatorGUI;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class InvestigatorSkill extends Skill implements ActiveSkill {


    public InvestigatorSkill() {
        super("Investigator", "See the inventory of a Player", SkillTier.UNIQUE, 600);
    }

    @Override
    public void onUse(Player player) {
        player.closeInventory();
        new InvestigatorGUI().open(player);
    }
}
