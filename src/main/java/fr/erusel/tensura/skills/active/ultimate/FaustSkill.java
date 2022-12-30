package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.PlayerChooseGUI;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class FaustSkill extends Skill implements ActiveSkill {


    public FaustSkill() {
        super("Faust, Lord of Investigation", Skills.FAUST, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 900, null);
        super.addLore("Know the stuff OR the skills of a player");
    }

    @Override
    
    public void onUse(Player player) {
        new PlayerChooseGUI(this, false, false).open(player);
    }
}
