package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FireballSkill extends Skill implements ActiveSkill {


    public FireballSkill() {
        super("Fireball", "throw a fireball where you're looking at", Skills.FIREBALL, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 600, null);
    }

    @Override
    public void onUse(Player player) {
        Location location = player.getLocation().add(0,1,0);
        player.getWorld().spawnEntity(location, EntityType.FIREBALL);
        activateCooldown();
    }
}
