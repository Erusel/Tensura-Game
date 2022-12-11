package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.GravityManipulationRunnable;
import org.bukkit.entity.Player;

public class GravityManipulationSkill extends Skill implements ActiveSkill {


    public GravityManipulationSkill() {
        super("Gravity Manipulation", "Allow you to fly for 10sec", Skills.GRAVITY, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 1300, null);
    }

    @Override
    public void onUse(Player player) {
        System.out.println("click");
        player.getAllowFlight();
        player.setAllowFlight(true);
        player.setFlying(true);
        System.out.println("fly oui");
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGravity(true);
        System.out.println("gravity oui");
        new GravityManipulationRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 10)
                .runTaskTimer(getMain(), 0, 20);
        System.out.println("run oui");
        activateCooldown();
    }
}