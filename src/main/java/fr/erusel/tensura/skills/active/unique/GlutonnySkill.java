package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class GlutonnySkill extends Skill implements ActiveSkill {


    public GlutonnySkill() {
        super("Glutonny", Skills.GLUTONNY, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 1200, Skills.BEELZEBUTH);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGlutonny(true);
        activateCooldown();
    }
}
