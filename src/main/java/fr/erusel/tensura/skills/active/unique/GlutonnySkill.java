package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.BeelzebuthSkill;
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
