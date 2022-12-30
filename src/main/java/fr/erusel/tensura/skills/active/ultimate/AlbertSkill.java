package fr.erusel.tensura.skills.active.ultimate;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class AlbertSkill extends Skill implements ActiveSkill {

    private final int DODGE = 5;

    public AlbertSkill() {
        super("Albert, Lord of Algebria",  Skills.ALBERT, SkillScope.OBTAINABLE, SkillTier.ULTIMATE, 1000, null);
        super.addLore("Dodge 5 attacks");
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
        activateCooldown();
    }
}
