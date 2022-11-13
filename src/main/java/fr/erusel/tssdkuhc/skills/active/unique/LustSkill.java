package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.active.ultimate.BeerusSkill;
import org.bukkit.entity.Player;

public class LustSkill extends Skill implements ActiveSkill {

    public LustSkill() {
        super("Lust", "Resurrect any dead players", SkillTier.UNIQUE, 2000, null);
    }

    @Override
    public void onUse(Player player) {
    }
}
