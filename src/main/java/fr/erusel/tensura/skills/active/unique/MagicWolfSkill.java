package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.skills.active.ultimate.RangaSkill;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

public class MagicWolfSkill extends Skill implements ActiveSkill {

    public MagicWolfSkill() {
        super("Magic Wolf", "Summon a powerful wolf for you", SkillTier.UNIQUE, 2000, RangaSkill.class);
    }

    @Override
    public void onUse(Player player) {
        Location location = player.getLocation();
        LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(location, EntityType.WOLF);
        ((Wolf)entity).setOwner(player);
        ((Wolf)entity).setTamed(true);
        ((Wolf)entity).setCustomName("§cRanga");
        ((Wolf)entity).setCustomNameVisible(true);
        ((Wolf)entity).setBreed(false);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*3);
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        activateCooldown();
    }
}
