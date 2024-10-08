package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class BeelzebuthSkill extends Skill implements ActiveSkill {


    public BeelzebuthSkill() {
        super("Beelzebuth, Lord of Gluttony", Skills.BEELZEBUTH, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 1200, null);
        super.addLore("Steal 2 skills from a killed player");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGlutonny(true);
        activateCooldown();
    }
}
