package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.active.ultimate.BeelzebuthSkill;
import org.bukkit.entity.Player;

public class GlutonnySkill extends Skill implements ActiveSkill {


    public GlutonnySkill() {
        super("Glutonny", "Steal 1 skill of your next kill", SkillTier.UNIQUE, 1200, BeelzebuthSkill.class);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGlutonny(true);
        activateCooldown();
    }
}
