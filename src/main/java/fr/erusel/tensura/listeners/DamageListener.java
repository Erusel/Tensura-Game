package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class DamageListener implements Listener {

    GameManager gameManager;
    ScoreBoardManager scoreBoardManager;
    PlayerManager playerManager;

    public DamageListener(GameManager gameManager, ScoreBoardManager scoreBoardManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.scoreBoardManager = scoreBoardManager;
        this.playerManager = playerManager;
    }



    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }

        // Scenarios
        for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onEntityDamageByEntity(event);

        if (gameManager.getGameState().equals(GState.PLAYING)){

            gameManager.getGameModeInstance().onEntityDamageByEntity(event);

            if(damaged instanceof Player player){
                GPlayer damagedGPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
                // Skill Use
                for (Skill skill : damagedGPlayer.getPlayerSkills()){
                    if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamageByEntity(event);
                }

                if (damagedGPlayer.getMathematicianDodgeLeft() >=1){
                    event.setCancelled(true);
                    damagedGPlayer.setMathematicianDodgeLeft(damagedGPlayer.getMathematicianDodgeLeft()-1);
                }

                if (damager instanceof Arrow projectile) {
                    LivingEntity shooter = (LivingEntity) projectile.getShooter();
                    if (damagedGPlayer.getRace().getName().equals(Races.ELF.getName())) {
                        int i = new Random().nextInt(100);
                        if (i <= 19) {
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
                if (damager instanceof Player player2){
                    GPlayer damagerGPlayer = playerManager.getGPlayerByUUID(player2.getUniqueId());
                    // Checking if the player is a Dwarf, and if they are, it is reducing the damage they take by 20%.
                    if (damagerGPlayer.isRace(Races.DWARF)) {
                        player.damage(event.getDamage()*0.8);
                    }
                    // Checking if the damager is a Majin, and if so, it has a 5% chance to apply the hunger effect to the
                    // damaged player.
                    if (damagerGPlayer.isRace(Races.MAJIN)) {
                        int i = new Random().nextInt(100);
                        if (i<=5) player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300,0));
                    }
                    if (damagerGPlayer.isImperceptibleActivated()) {
                        event.setCancelled(true);
                    }
                    if (damagerGPlayer.isOppressorActivated()){
                        damaged.setVelocity(player2.getLocation().getDirection().setY(0).normalize().multiply(2));
                    }
                    damagerGPlayer.setTrackingPlayer(player.getUniqueId());
                }


            }
            if (damager instanceof Player player2){
                GPlayer damagerGPlayer = playerManager.getGPlayerByUUID(player2.getUniqueId());

                // Checking if the player is a demon, and if they are, it has a 5% chance of setting the player on fire for 10sec.
                if (damagerGPlayer.isRace(Races.DEMON)) {
                    int i = new Random().nextInt(100);
                    if (i<=5) damaged.setFireTicks(200);
                }
            }
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }
        for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onDamage(event);

        for (Skill skill: playerManager.getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill passiveSkill) passiveSkill.onDamage(event);
        }
    }


}
