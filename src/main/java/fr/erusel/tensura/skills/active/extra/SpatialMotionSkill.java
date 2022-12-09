package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Player;

public class SpatialMotionSkill extends Skill implements ActiveSkill {


    public SpatialMotionSkill() {
        super("Spatial motin", "Place a TP point and teleport you on demand", Skills.SPATIALMOTION, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 600, null);
    }

    @Override
    public void onUse(Player player) {
        // A faire
        activateCooldown();
    }
}
