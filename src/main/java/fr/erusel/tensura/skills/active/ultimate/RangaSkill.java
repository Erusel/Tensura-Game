package fr.erusel.tensura.skills.active.ultimate;

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

public class RangaSkill extends Skill implements ActiveSkill {


    public RangaSkill() {
        super("Ranga, Lord of the toutous", "Summon powerfuls wolf for you", Skills.RANGA, SkillScope.OBTAINABLE,  SkillTier.ULTIMATE, 900, null);
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
        entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()*1.5);
        entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        activateCooldown();
    }
}

