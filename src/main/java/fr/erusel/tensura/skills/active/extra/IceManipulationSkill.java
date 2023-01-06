package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IceManipulationSkill extends Skill implements ExtraSkill, Eventable {


    public IceManipulationSkill() {
        super("Ice Manipulation", Skills.ICEMANIPULATION, SkillScope.UNOBTAINABLE, SkillTier.EXTRA, 800, null);
        super.addLore("Use the power of the Ice");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Bring the Ice Age";
    }

    @Override
    public void onRightClick(Player player) {
        // transform blocks in a radius of 10 blocks into packed ice
        for (int x = -10; x < 10; x++) {
            for (int y = -5; y < 10; y++) {
                for (int z = -10; z < 10; z++) {
                    Location location = player.getLocation().add(x, y, z);
                    if (location.getBlock().getType().isSolid()) {
                        if (!(location.getBlock().getType() == Material.BEDROCK)) {
                            location.getBlock().setType(Material.PACKED_ICE);
                        }
                    }
                }
            }
        }
        // apply slowness effect to players in a radius of 10 blocks
        for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10)) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
            }
        }
        activateCooldown();
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Freeze the next opponent you hit";
    }

    @Override
    public void onLeftClick(Player player) {
        // the next entity that the player will hit will be frozen (slowness 4 effect)
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFrozenHit(true);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity livingEntity)){
            return;
        }
        if (getPlayerManager().getGPlayerByUUID(event.getDamager().getUniqueId()).isFrozenHitActivated()) {
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 160, 3));
        }
    }
}
