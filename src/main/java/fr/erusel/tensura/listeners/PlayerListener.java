package fr.erusel.tensura.listeners;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Prefixs;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerListener implements Listener {

    GameManager gameManager;
    ScoreBoardManager scoreBoardManager;
    PlayerManager playerManager;

    public PlayerListener(GameManager gameManager, ScoreBoardManager scoreBoardManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.scoreBoardManager = scoreBoardManager;
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        event.setJoinMessage("§7[§5+§7] " + player.getDisplayName());
        Utils.resetPlayer(player);
        scoreBoardManager.initializeScoreboard(player);

        if (gameManager.getGameState().equals(GState.WAITING)){
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            scoreBoardManager.refreshWaitingScoreboard();
            if (!(gameManager.getPlayerList().size() >= gameManager.getMaxPlayer())){
                gameManager.getPlayerList().add(player.getUniqueId());
            }else {
                gameManager.addWaitingList(player.getUniqueId());
                player.sendMessage("§cToo many players, added in waiting list !");
            }
        } else if (gameManager.getGameState().equals(GState.PLAYING)) {
            gameManager.getGameModeInstance().refreshScoreboard();
            gameManager.getGameModeInstance().onPlayerJoin(event);
            for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onPlayerJoin(event);
        } else {
            scoreBoardManager.refreshWaitingScoreboard();
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§c-§7] " + player.getDisplayName());
        FastBoard board = scoreBoardManager.scoreboard.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
        if (gameManager.getGameState().equals(GState.WAITING)){
            if (gameManager.getPlayerList().contains(player.getUniqueId())){
                gameManager.getPlayerList().remove(player.getUniqueId());
                if (gameManager.getWaitingList().size() > 0){
                    gameManager.getPlayerList().add(gameManager.getWaitingList().get(0));
                    Bukkit.getPlayer(gameManager.getWaitingList().get(0)).sendMessage("§aA player leaved, you get is place !");
                    gameManager.removeWaitingList(gameManager.getWaitingList().get(0));
                }
            }else if (gameManager.getWaitingList().contains(player.getUniqueId())) gameManager.removeWaitingList(player.getUniqueId());
        }
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerLeave(event);
            for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onPlayerLeave(event);

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        GPlayer gKiller = playerManager.getGPlayerByUUID(killer.getUniqueId());
        GPlayer gPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
        gKiller.addKill(1);
        event.setDeathMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Player §c" + player.getName() + " §3died against §c" + killer.getName());

        for (Skill skill : gKiller.getPlayerSkills()) if (skill instanceof PassiveSkill) ((PassiveSkill) skill).onKill(killer, player);
        for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onPlayerDeath(event);

        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerDeath(event);
        }

        if (gKiller.isGlutonnyActivated()){
            int i = new Random().nextInt(gPlayer.getPlayerSkills().size()-1);
            Skill skill = gPlayer.getPlayerSkills().get(i);
            gKiller.addSkill(skill);
            gPlayer.removeSkill(skill);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        GPlayer gPlayer = playerManager.getGPlayerByUUID(player.getUniqueId());
        player.setGameMode(GameMode.SPECTATOR);
        gameManager.addDeadPlayer(player.getUniqueId());
        gameManager.removeAlivePlayer(player.getUniqueId());
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerRespawn(event);
        }
        if (gPlayer.canRessurect() && gPlayer.haveSkill(Skills.OSIRIS)){
            gPlayer.ressurect();
            gPlayer.setCanResurrect(false);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }
        if(damaged instanceof Player){
            for (Skill skill : playerManager.getGPlayerByUUID(damaged.getUniqueId()).getPlayerSkills()){
                if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamageByEntity(event);
            }
            if (playerManager.getGPlayerByUUID(damaged.getUniqueId()).isReflectorActivated()) {
                if (damager instanceof Arrow projectile) {
                    LivingEntity shooter = (LivingEntity) projectile.getShooter();
                    shooter.damage(event.getDamage());
                    event.setCancelled(true);
                }
                ((LivingEntity) damager).damage(event.getDamage());
                event.setCancelled(true);
            }
            if (damager instanceof Arrow projectile) {
                LivingEntity shooter = (LivingEntity) projectile.getShooter();
                if (playerManager.getGPlayerByUUID(shooter.getUniqueId()).getRace().getName().equals(Races.ELF.getName())) {
                    int i = new Random().nextInt(100);
                    if (i <= 19) {
                        ((Player) damaged).damage(event.getDamage()*1.2);
                        shooter.sendMessage("§6x1.2 damage !");
                        }
                    }
            }
            for (Scenario scenario : gameManager.getActivatedScenariosInstance()) scenario.onEntityDamageByEntity(event);

            if (damager instanceof Player player){
                // Checking if the damager is a Majin, and if so, it has a 5% chance to apply the hunger effect to the
                // damaged player.
                if (playerManager.getGPlayerByUUID(damager.getUniqueId()).getRace().getName().equals(Races.MAJIN.getName())) {
                    int i = new Random().nextInt(100);
                    if (i<=5) player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300,0));
                }
                // Checking if the player is a demon, and if they are, it has a 5% chance of setting the player on fire for 10sec.
                if (playerManager.getGPlayerByUUID(damager.getUniqueId()).getRace().getName().equals(Races.DEMON.getName())) {
                    int i = new Random().nextInt(100);
                    if (i<=5) damaged.setFireTicks(200);
                }
                // Checking if the player is a Dwarf, and if they are, it is reducing the damage they take by 20%.
                if (playerManager.getGPlayerByUUID(damaged.getUniqueId()).getRace().getName().equals(Races.DWARF.getName())) {
                    player.damage(event.getDamage()*0.8);
                }
                playerManager.getGPlayerByUUID(damager.getUniqueId()).setTrackingPlayer(damaged.getUniqueId());
            }
        }
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onEntityDamageByEntity(event);
        }
        if (playerManager.getGPlayerByUUID(damager.getUniqueId()).isImperceptibleActivated()) {
            event.setCancelled(true);
        }
        if (playerManager.getGPlayerByUUID(damager.getUniqueId()).isOppressorActivated()){
            damaged.setVelocity(damager.getLocation().getDirection().setY(0).normalize().multiply(2));
        }
        if (playerManager.getGPlayerByUUID(damaged.getUniqueId()).getMathematicianDodgeLeft() >=1){
            event.setCancelled(true);
            playerManager.getGPlayerByUUID(damaged.getUniqueId()).setMathematicianDodgeLeft(playerManager.getGPlayerByUUID(damaged.getUniqueId()).getMathematicianDodgeLeft()-1);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
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

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerMove(event);
            if (playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).isInHarvestFestival()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }

        gameManager.getGameModeInstance().onBlockBreak(event);
        for (Skill skill: playerManager.getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill passiveSkill) passiveSkill.onBlockBreak(event);
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Bukkit.broadcastMessage("§8" + event.getPlayer().getName() + " §6> §7" + event.getMessage());
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onChat(event);
        }
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event){
        if (gameManager.getGameState().equals(GState.PLAYING)){
            Player player = event.getPlayer();
            gameManager.getGameModeInstance().onAdvancement(event);
            if (gameManager.getPlayerList().contains(player.getUniqueId())){
                int i = new Random().nextInt(100);
                if (i < 6){
                    player.sendMessage("§3You gain an skill");
                }
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (gameManager.getGameState().equals(GState.WAITING)) event.setCancelled(true);
        if (!(event.getEntity() instanceof Player player)) return;
        if (gameManager.isRaceActivated()){
            if (playerManager.getGPlayerByUUID(player.getUniqueId()).getRace().getName().equals(Races.SLIME.getName())) event.setCancelled(true);

        }
    }
}
