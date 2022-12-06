package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.GravityManipulationRunnable;
import org.bukkit.entity.Player;

public class GravityManipulationSkill extends Skill implements ActiveSkill {


    public GravityManipulationSkill() {
        super("Gravity Manipulation", "Allow you to fly for 10sec", Skills.FLETCHER, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 1300, null);
    }

    @Override
    public void onUse(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGravity(true);
        new GravityManipulationRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 10)
                .runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}