package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.PlayerChooseGUI;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class OsirisSkill extends Skill implements ActiveSkill {

    public OsirisSkill() {
        super("Osiris, Lord of resurrection", Skills.OSIRIS, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 2000, null);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        new PlayerChooseGUI(this, true, true).open(player);
    }
}
