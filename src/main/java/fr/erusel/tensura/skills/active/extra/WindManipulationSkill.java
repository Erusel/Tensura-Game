package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WindManipulationSkill extends Skill implements ActiveSkill {


    public WindManipulationSkill() {
        super("Wind Manipulation", "Knockback away near entities", Skills.WINDMANIPULATION, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
    }

    @Override
    public void onUse(Player player) {
        //Knockback away near entities in a radius of 10 blocks in the opposite direction
        for(Entity entity: player.getNearbyEntities(10,10,10)) {
            entity.setVelocity(entity.getLocation().getDirection().setY(0).normalize().multiply(3));
        }
        activateCooldown();
    }
}
