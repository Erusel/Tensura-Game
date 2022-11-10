package fr.erusel.tssdkuhc.skills.active.unique;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class MathematicianSkill extends Skill implements ActiveSkill {

    private int DODGE = 3;

    public MathematicianSkill() {
        super("Mathematician", "Ignore the next 3 damage", SkillTier.UNIQUE, 1000);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
    }
}
