package fr.erusel.tensura.listeners;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Prefixs;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.*;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
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
import org.bukkit.projectiles.ProjectileSource;

import java.util.Random;

public class PlayerListener implements Listener {

    GameManager gameManager = Main.getInstance().getGameManager();
    ScoreBoardManager scoreBoardManager = Main.getInstance().getScoreboardManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        event.setJoinMessage("§7[§5+§7] " + player.getDisplayName());
        Utils.resetPlayer(player);
        FastBoard board = new FastBoard(player);
        board.updateTitle("§bTensura §3Game");
        Main.getInstance().getScoreboardManager().scoreboard.put(player.getUniqueId(), board);

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
            scoreBoardManager.refreshPlayingScoreboard();
            gameManager.getGameModeInstance().onPlayerJoin(event);
            for (Scenario scenario : Main.getInstance().getGameManager().getActivatedScenariosInstance()) scenario.onPlayerJoin(event);
        } else {
            scoreBoardManager.refreshWaitingScoreboard();
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§c-§7] " + player.getDisplayName());
        FastBoard board = Main.getInstance().getScoreboardManager().scoreboard.remove(player.getUniqueId());
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
            for (Scenario scenario : Main.getInstance().getGameManager().getActivatedScenariosInstance()) scenario.onPlayerLeave(event);

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        GPlayer gKiller = Main.getInstance().getPlayerManager().getGPlayerByUUID(killer.getUniqueId());
        GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId());
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
        GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        player.setGameMode(GameMode.SPECTATOR);
        gameManager.addDeadPlayer(player.getUniqueId());
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
            for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getPlayerSkills()){
                if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamageByEntity(event);
            }
            if (Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).isReflectorActivated()) {
                if (damager instanceof Arrow) {
                    Projectile projectile = (Projectile) damager;
                    LivingEntity shooter = (LivingEntity) projectile.getShooter();
                    shooter.damage(event.getDamage());
                    event.setCancelled(true);
                }
                ((LivingEntity) damager).damage(event.getDamage());
                event.setCancelled(true);
            }
            if (damager instanceof Arrow) {
                Projectile projectile = (Projectile) damager;
                LivingEntity shooter = (LivingEntity) projectile.getShooter();
                if (Main.getInstance().getPlayerManager().getGPlayerByUUID(shooter.getUniqueId()).getRace().getName().equals(Races.ELF.getName())) {
                    int i = new Random().nextInt(100);
                    if (i <= 19) {
                        ((Player) damaged).damage(event.getDamage()*1.2);
                        shooter.sendMessage("§6x1.2 damage !");
                        }
                    }
            }
            for (Scenario scenario : Main.getInstance().getGameManager().getActivatedScenariosInstance()) scenario.onEntityDamageByEntity(event);

            if (damager instanceof Player){
                if (Main.getInstance().getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).getRace().getName().equals(Races.MAJIN.getName())) {
                    int i = new Random().nextInt(100);
                    if (i<=5) {
                        ((Player) damaged).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300,0));
                    }
                }
                if (Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getRace().getName().equals(Races.MAJIN.getName())) {
                    if (((Player) damager).getInventory().getItemInMainHand().containsEnchantment(Enchantment.DAMAGE_UNDEAD)) {
                        ((Player) damaged).damage(((Player) damager).getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*1.3);
                    }
                }
                Main.getInstance().getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).setTrackingPlayer(damaged.getUniqueId());
            }
        }
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onEntityDamageByEntity(event);
        }
        if (Main.getInstance().getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).isImperceptibleActivated()) {
            event.setCancelled(true);
        }
        if (Main.getInstance().getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).isOppressorActivated()){
            damaged.setVelocity(damager.getLocation().getDirection().setY(0).normalize().multiply(2));
        }
        if (Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getMathematicianDodgeLeft() >=1){
            event.setCancelled(true);
            Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).setMathematicianDodgeLeft(Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getMathematicianDodgeLeft()-1);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }
        Player player = (Player) event.getEntity();
        for (Scenario scenario : Main.getInstance().getGameManager().getActivatedScenariosInstance()) scenario.onDamage(event);

        for (Skill skill: Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamage(event);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (gameManager.getGameState().equals(GState.PLAYING)){
            gameManager.getGameModeInstance().onPlayerMove(event);
            if (Main.getInstance().getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).isInHarvestFestival()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getGameState().equals(GState.PLAYING)){
            event.setCancelled(true);
            return;
        }

        gameManager.getGameModeInstance().onBlockBreak(event);
        for (Skill skill: Main.getInstance().getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onBlockBreak(event);
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
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().equals(Races.SLIME)) event.setCancelled(true);
    }
}
