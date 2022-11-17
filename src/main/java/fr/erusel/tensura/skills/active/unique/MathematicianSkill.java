package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.AlbertSkill;
import org.bukkit.entity.Player;

public class MathematicianSkill extends Skill implements ActiveSkill {

    private final int DODGE = 3;

    public MathematicianSkill() {
        super("Mathematician", "Ignore the next 3 damage", SkillTier.UNIQUE, 1000, AlbertSkill.class);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
        activateCooldown();
    }
}
