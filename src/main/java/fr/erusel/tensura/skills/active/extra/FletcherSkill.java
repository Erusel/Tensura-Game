package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class FletcherSkill extends Skill implements ExtraSkill, Eventable {

    Random random = new Random();

    public FletcherSkill() {
        super("Fletcher", Skills.FLETCHER, SkillScope.UNOBTAINABLE,  SkillTier.EXTRA, 800, null);
        super.addLore("Upgrade your bow");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Change next arrow effect";
    }


    @Override
    public void onRightClick(Player player) {
        PotionEffectType[] potionsDebuff = {
                PotionEffectType.SLOW,
                PotionEffectType.POISON,
                PotionEffectType.WEAKNESS,
                PotionEffectType.SLOW_DIGGING,
                PotionEffectType.BLINDNESS,
                PotionEffectType.CONFUSION,
                PotionEffectType.HUNGER,
        };
        int i = random.nextInt(potionsDebuff.length);
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherEffect(potionsDebuff[i]);
        player.sendMessage("§6Your next arrow will inflict " + potionsDebuff[i].getName());
        activateCooldown();
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Throw 3 more arrows when you shoot";
    }


    @Override
    public void onLeftClick(Player player) {
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherBurst(true);
        activateCooldown();
    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        if (!(damager instanceof Arrow arrow)) {
              return;
        }
        if (!(arrow.getShooter() instanceof Player player)) {
            return;
        }
        if (getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getFletcherEffect() == null) {
            return;
        }
        if (!(entity instanceof LivingEntity livingEntity)) {
            return;
        }
        livingEntity.addPotionEffect(new PotionEffect(getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getFletcherEffect(), 200, 0));
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherEffect(null);
    }

    @Override
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (!getPlayerManager().getGPlayerByUUID(player.getUniqueId()).isFletcherBurst()) {
            return;
        }
        new BukkitRunnable() {
            int k=0;
            @Override
            public void run() {
                if (k == 3) {
                    getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherBurst(false);
                    cancel();
                }
                if (k != 0) {
                    Vector direction = player.getLocation().getDirection();
                    player.playSound(player.getLocation(), "minecraft:entity.arrow.shoot", 1, 1);
                    Arrow shootedArrow = player.launchProjectile(Arrow.class);
                    shootedArrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                    shootedArrow.setVelocity(new Vector(direction.getX(), direction.getY(), direction.getZ()).normalize().multiply(3));
                }
                k++;
            }
        }.runTaskTimer(Main.getInstance(), 0L, 10L);
    }
}
