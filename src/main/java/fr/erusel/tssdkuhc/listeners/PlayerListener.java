package fr.erusel.tssdkuhc.listeners;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.GState;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.managers.GameManager;
import fr.erusel.tssdkuhc.managers.ScoreBoardManager;
import fr.erusel.tssdkuhc.objects.GPlayer;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.skills.active.ultimate.MalarSkill;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PlayerListener implements Listener {

    Material[] ores = {Material.IRON_ORE, Material.COAL_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.GOLD_ORE};
    GameManager gameManager = Main.getInstance().getGameManager();
    ScoreBoardManager scoreBoardManager = Main.getInstance().getScoreboardManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        event.setJoinMessage("§7[§5+§7] " + player.getDisplayName());

        FastBoard board = new FastBoard(player);
        board.updateTitle("§bTensura §3Game");
        Main.getInstance().getScoreboardManager().scoreboard.put(player.getUniqueId(), board);

        scoreBoardManager.refreshWaitingScoreboard();
        if (gameManager.getGameState().equals(GState.WAITING)){
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
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
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();
        if(damaged instanceof Player){
            if (damager instanceof Player){
                Main.getInstance().getPlayerManager().getGPlayerByUUID(damager.getUniqueId()).setTrackingPlayer(damaged.getUniqueId());
            }
        }
        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(damaged.getUniqueId()).getPlayerSkills()){
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamageByEntity(event);
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
        Player player = (Player) event.getEntity();
        for (Skill skill: Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onDamage(event);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (Main.getInstance().getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).isInHarvestFestival()) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() == null) return;
        for (Skill skill: Main.getInstance().getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).getPlayerSkills()) {
            if (skill instanceof PassiveSkill) ((PassiveSkill)skill).onBlockBreak(event);
        }
    }
}


