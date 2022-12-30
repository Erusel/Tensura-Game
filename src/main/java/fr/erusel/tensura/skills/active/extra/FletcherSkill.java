package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.FletcherBurstRunnable;
import fr.erusel.tensura.threads.skills.ImperceptibleRunnable;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class FletcherSkill extends Skill implements ExtraSkill, Eventable {


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
        int i = new Random().nextInt(potionsDebuff.length);
        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherEffect(potionsDebuff[i]);
        player.sendMessage("§6Your next arrow will inflict " + potionsDebuff[i].getName());
        activateCooldown();
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Shoot 3 arrows in succession";
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
        if (damager instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player player) {
                if (getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getFletcherEffect() != null) {
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.addPotionEffect(new PotionEffect(getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getFletcherEffect(), 200, 0));
                        getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setFletcherEffect(null);
                    }
                }
            }
        }
    }

    @Override
    public void onEntityShootBow(EntityShootBowEvent event) {
        //TODO: Add a cooldown beetwen each arrow shot (1s)
        if (event.getEntity() instanceof Player player) {
            if (getPlayerManager().getGPlayerByUUID(player.getUniqueId()).isFletcherBurst()) {
                new FletcherBurstRunnable(getPlayerManager().getGPlayerByUUID(player.getUniqueId()), 3)
                        .runTaskTimer(getMain(), 0, 20);
                for (int i = 0; i < 3; i++) {
                    double x = event.getProjectile().getVelocity().getX();
                    double y = event.getProjectile().getVelocity().getY();
                    double z = event.getProjectile().getVelocity().getZ();
                    player.launchProjectile(Arrow.class, player.getLocation().getDirection()).setVelocity(event.getProjectile().getVelocity().setX(x).setY(y).setZ(z));
                }
            }
        }
    }
}