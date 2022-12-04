package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.AlbertSkill;
import org.bukkit.entity.Player;

public class MathematicianSkill extends Skill implements ActiveSkill {

    private final int DODGE = 3;

    public MathematicianSkill() {
        super("Mathematician", "Ignore the next 3 damage", Skills.MATHEMATICIAN, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 1000, AlbertSkill.class);
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
        activateCooldown();
    }
}
