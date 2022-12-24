package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class MathematicianSkill extends Skill implements ActiveSkill {

    private final int DODGE = 3;

    public MathematicianSkill() {
        super("Mathematician", Skills.MATHEMATICIAN, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 1000, Skills.ALBERT);
        super.addLore("Lore TODO");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
        activateCooldown();
    }
}
