package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.scoreboards.CharybdisScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;


public class CharybdisMode extends Mode {
    public CharybdisMode() {
        super("Charybdis Hunt", Modes.CHARYBDIS, new CharybdisScoreboard(), true);
    }

    Random random = new Random();

    @Override
    public void teleportPlayers() {

        int borderRadius = getGameSettingManager().getBorderRadius();
        int redTeamX = random.nextInt(borderRadius);
        int redTeamZ = random.nextInt(borderRadius);
        Location redTeamSpawn = getWorldManager().getMap().getHighestBlockAt(redTeamX, redTeamZ).getLocation();
        int blueTeamX = random.nextInt(borderRadius);
        int blueTeamZ = random.nextInt(borderRadius);
        Location blueTeamSpawn = getWorldManager().getMap().getHighestBlockAt(blueTeamX, blueTeamZ).getLocation();
        int greenTeamX = random.nextInt(borderRadius);
        int greenTeamZ = random.nextInt(borderRadius);
        Location greenTeamSpawn = getWorldManager().getMap().getHighestBlockAt(greenTeamX, greenTeamZ).getLocation();
        int yellowTeamX = random.nextInt(borderRadius);
        int yellowTeamZ = random.nextInt(borderRadius);
        Location yellowTeamSpawn = getWorldManager().getMap().getHighestBlockAt(yellowTeamX, yellowTeamZ).getLocation();

        // Teleport Players with teams
        for (Player player : Bukkit.getOnlinePlayers()){
            switch (getTeamManager().getPlayerTeam(player.getUniqueId())){
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

}
