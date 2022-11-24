package fr.erusel.tensura.listeners;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Prefixs;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.PassiveSkill;
import fr.erusel.tensura.objects.Skill;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerListener implements Listener {

    GameManager gameManager = Main.getInstance().getGameManager();
    ScoreBoardManager scoreBoardManager = Main.getInstance().getScoreboardManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        event.setJoinMessage("§7[§5+§7] " + player.getDisplayName());

        FastBoard board = new FastBoard(player);
        board.updateTitle("§bTensura §3Game");
        Main.getInstance().getScoreboardManager().scoreboard.put(player.getUniqueId(), board);

        if (gameManager.getGameState().equals(GState.WAITING)){
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            scoreBoardManager.refreshWaitingScoreboard();
        } else if (gameManager.getGameState().equals(GState.PLAYING)) {
            scoreBoardManager.refreshPlayingScoreboard();
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
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        GPlayer gKiller = Main.getInstance().getPlayerManager().getGPlayerByUUID(killer.getUniqueId());
        GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId());
        gKiller.addKill(1);
        event.setDeathMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Player " + player.getName() + " is dead");
        for (Skill skill : gKiller.getPlayerSkills()) {
            if (skill instanceof PassiveSkill) {
                ((PassiveSkill) skill).onKill(killer, player);
            }
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
        Main.getInstance().getGameManager().addDeadPlayer(player.getUniqueId());
        if (gPlayer.canRessurect() && gPlayer.haveSkill(Skills.OSIRIS)){
            gPlayer.ressurect();
            gPlayer.setCanResurrect(false);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();
        if(damaged instanceof Player){
            for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getPlayerSkills()){
                if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamageByEntity(event);
            }
            if (damager instanceof Player){
                Main.getInstance().getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).setTrackingPlayer(damaged.getUniqueId());
            }
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
        if (!(Main.getInstance().getGameManager().getGameState().equals(GState.PLAYING))) return;
        Player player = (Player) event.getEntity();
        for (Skill skill: Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamage(event);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (Main.getInstance().getGameManager().getGameState().equals(GState.PLAYING)){
            if (Main.getInstance().getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).isInHarvestFestival()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        for (Skill skill: Main.getInstance().getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onBlockBreak(event);
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setMessage("§8" + event.getPlayer().getName() + " §6> §7" + event.getMessage());
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event){
        if (Main.getInstance().getGameManager().getGameState().equals(GState.PLAYING)){
            Player player = event.getPlayer();
            if (Main.getInstance().getGameManager().getPlayerList().contains(player.getUniqueId())){
                int i = new Random().nextInt(100);
                if (i < 6){
                    player.sendMessage("§3You gain an skill");
                }
            }
        }
    }
}


