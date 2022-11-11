package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class BeelzebuthSkill extends Skill implements ActiveSkill {


    public BeelzebuthSkill() {
        super("Beelzebuth, Lord of Gluttony", "", SkillTier.ULTIMATE, 1200, null);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGlutonny(true);
    }
}
