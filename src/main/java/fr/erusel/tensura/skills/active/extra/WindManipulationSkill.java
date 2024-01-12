package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.GravityManipulationRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WindManipulationSkill extends Skill implements ExtraSkill {


    public WindManipulationSkill() {
        super("Wind Manipulation",  Skills.WINDMANIPULATION, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
        super.addLore("Use the power of the Wind");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Fly away temporarily";
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Knockback away around players";
    }

    @Override
    public void onRightClick(Player player) {
        player.getAllowFlight();
        player.setAllowFlight(true);
        player.setFlying(true);
        player.sendMessage("§6You are now able to fly for 5 seconds");
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setGravity(true);
        new GravityManipulationRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 5)
                .runTaskTimer(getMain(), 0, 20);
        activateCooldown();
    }

    @Override
    public void onLeftClick(Player player) {
        //Knockback away near entities in a radius of 10 blocks in the opposite direction
        for(Entity entity: player.getNearbyEntities(10,10,10)) {
            entity.setVelocity(entity.getLocation().getDirection().setY(0).normalize().multiply(3));
        }
        activateCooldown();
    }
}
