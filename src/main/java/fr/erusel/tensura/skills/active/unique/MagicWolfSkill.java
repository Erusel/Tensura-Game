package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.OsirisSkill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MagicWolfSkill extends Skill implements ActiveSkill {

    public MagicWolfSkill() {
        super("Magic Wolf", "Summon a powerful wolf for you", SkillTier.UNIQUE, 2000, OsirisSkill.class);
    }

    @Override
    public void onUse(Player player) {
        Location location = player.getLocation();
        Bukkit.getWorld(player.getUniqueId()).spawnEntity(location, EntityType.WOLF);
    }
}
