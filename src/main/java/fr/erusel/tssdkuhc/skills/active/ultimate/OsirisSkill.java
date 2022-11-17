package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.inventorys.skills.LustGUI;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class OsirisSkill extends Skill implements ActiveSkill {

    public OsirisSkill() {
        super("Osiris, Lord of resurrection", "Resurrect any dead players and give you second life", SkillTier.ULTIMATE, 2000, null);
    }

    @Override
    public void onUse(Player player) {
        new LustGUI(this, player).open(player);
    }
}
