package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.scoreboards.CharybdisScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Random;


public class CharybdisMode extends Mode {
    public CharybdisMode() {
        super("Charybdis Hunt", Modes.CHARYBDIS, new CharybdisScoreboard(), true);
    }

    @Override
    public void teleportPlayers() {

        int borderRadius = getGameManager().getBorderRadius();
        int redTeamX = new Random().nextInt(borderRadius);
        int redTeamZ = new Random().nextInt(borderRadius);
        Location redTeamSpawn = getWorldManager().getMap().getHighestBlockAt(redTeamX, redTeamZ).getLocation();
        int blueTeamX = new Random().nextInt(borderRadius);
        int blueTeamZ = new Random().nextInt(borderRadius);
        Location blueTeamSpawn = getWorldManager().getMap().getHighestBlockAt(blueTeamX, blueTeamZ).getLocation();
        int greenTeamX = new Random().nextInt(borderRadius);
        int greenTeamZ = new Random().nextInt(borderRadius);
        Location greenTeamSpawn = getWorldManager().getMap().getHighestBlockAt(greenTeamX, greenTeamZ).getLocation();
        int yellowTeamX = new Random().nextInt(borderRadius);
        int yellowTeamZ = new Random().nextInt(borderRadius);
        Location yellowTeamSpawn = getWorldManager().getMap().getHighestBlockAt(yellowTeamX, yellowTeamZ).getLocation();

        // Teleport Players with teams
        for (Player player : Bukkit.getOnlinePlayers()){
            switch (getGameManager().getTeamManager().getPlayerTeam(player.getUniqueId())){
                case "Red":
                    player.teleport(redTeamSpawn);
                case "Blue":
                    player.teleport(blueTeamSpawn);
                case "Green":
                    player.teleport(greenTeamSpawn);
                case "Yellow":
                    player.teleport(yellowTeamSpawn);
                case "None":
                    player.teleport(getWorldManager().getMap().getSpawnLocation());
            }
            onPlayerSpawn(player);
        }
    }

    @Override
    public void onPlayerSpawn(Player player) {

    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§7-------------§5Charybdis Hunt§7-------------");
        Bukkit.broadcastMessage("§7Welcome in Charybdis Hunt");
        Bukkit.broadcastMessage("§7You and your team you need to collect");
        Bukkit.broadcastMessage("§7every part of Charybdis for winning !");
        Bukkit.broadcastMessage("§7-------------------§6HINT-------------------");
        Bukkit.broadcastMessage("§7You can craft a §6Compass§7 for find the");
        Bukkit.broadcastMessage("§7sanctuaries where charybdis part are hidden !");
        Bukkit.broadcastMessage("§7--------------------------------------------");
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent event) {

    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {

    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {

    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {

    }

    @Override
    public void onChat(AsyncPlayerChatEvent event) {

    }

    @Override
    public void onAdvancement(PlayerAdvancementDoneEvent event) {

    }
}
