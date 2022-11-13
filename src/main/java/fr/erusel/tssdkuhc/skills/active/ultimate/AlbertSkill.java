package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.entity.Player;

public class AlbertSkill extends Skill implements ActiveSkill {

    private int DODGE = 3;

    public AlbertSkill() {
        super("Albert, Lord of Algebria", "Ignore the next 3 damage", SkillTier.ULTIMATE, 1000, null);
    }

    @Override
    public void onUse(Player player) {
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setMathematicianDodgeLeft(DODGE);
        activateCooldown();
    }
}
