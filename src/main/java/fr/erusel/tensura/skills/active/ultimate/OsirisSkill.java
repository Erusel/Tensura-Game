package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.inventorys.PlayerChooseGUI;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class OsirisSkill extends Skill implements ActiveSkill {

    public OsirisSkill() {
        super("Osiris, Lord of resurrection", "Resurrect any dead players and give you second life", SkillTier.ULTIMATE, 2000, null);
    }

    @Override
    public void onUse(Player player) {
        new PlayerChooseGUI(this).open(player);
    }
}
