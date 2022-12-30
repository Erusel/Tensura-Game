package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.GPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class EntityDamageByEntityListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;
    Random random = new Random();

    public EntityDamageByEntityListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }

        gameManager.getGameModeInstance().onEntityDamageByEntity(event);
        gameManager.getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onEntityDamageByEntity(event));

        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if (!(damaged instanceof Player player)){
            return;
        }

        GPlayer damagedGPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
        damagedGPlayer.getPlayerSkills().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(s -> ((Eventable) s).onEntityDamageByEntity(event));
        if (damagedGPlayer.getMathematicianDodgeLeft() >=1){
            event.setCancelled(true);
            damagedGPlayer.setMathematicianDodgeLeft(damagedGPlayer.getMathematicianDodgeLeft()-1);
        }
        if (damager instanceof Player player2){
            GPlayer damagerGPlayer = playerManager.getGPlayerByUUID(player2.getUniqueId());

            switch (damagerGPlayer.getRaces()){
                case DEMON -> {
                    if (random.nextInt(100)<=5) {
                        damaged.setFireTicks(200);
                    }
                }
                case DWARF -> player.damage(event.getDamage()*0.8);
                case MAJIN -> {
                    if (random.nextInt(100)<=5) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300,0));
                    }
                }
            }

            event.setCancelled(damagedGPlayer.isImperceptibleActivated());
            damagerGPlayer.setTrackingPlayer(player.getUniqueId());
            if (damagerGPlayer.isOppressorActivated()){
                damaged.setVelocity(player2.getLocation().getDirection().setY(0).normalize().multiply(2));
            }
            if (player2.getAllowFlight()) {
                if (!((Player) damager).getGameMode().equals(GameMode.CREATIVE)) {
                    event.setCancelled(true);
                }
            }
            return;
        }

        if (!(damager instanceof Arrow projectile)) {
            return;
        }

        LivingEntity shooter = (LivingEntity) projectile.getShooter();
        if (damagedGPlayer.isRace(Races.ELF)) {
            if (random.nextInt(100) <= 19) {
                player.damage(event.getDamage()*1.2);
                shooter.sendMessage("§6x1.2 damage !");
            }
        }
        if (damagedGPlayer.isReflectorActivated()) {
            shooter.damage(event.getDamage());
            ((LivingEntity) damager).damage(event.getDamage());
            event.setCancelled(true);
        }
    }
}
