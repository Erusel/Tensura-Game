package fr.erusel.tensura.skills.active.unique;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ActiveSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class MagicWolfSkill extends Skill implements ActiveSkill {

    public MagicWolfSkill() {
        super("Magic Wolf", Skills.MAGICWOLF, SkillScope.OBTAINABLE,  SkillTier.UNIQUE, 2000, Skills.RANGA);
        super.addLore("Lore TODO");
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
