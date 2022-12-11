package fr.erusel.tensura.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamManager {
    
    private static TeamManager instance;
    
    
    private final List<UUID> redTeamPlayers = new ArrayList<>();
    private final List<UUID> blueTeamPlayers = new ArrayList<>();
    private final List<UUID> greenTeamPlayers = new ArrayList<>();
    private final List<UUID> yellowTeamPlayers = new ArrayList<>();


    public String getPlayerTeam(UUID uuid){
        if (redTeamPlayers.contains(uuid)) return "Red";
        if (blueTeamPlayers.contains(uuid)) return "Blue";
        if (greenTeamPlayers.contains(uuid)) return "Green";
        if (yellowTeamPlayers.contains(uuid)) return "Yellow";
        return "None";
    }


    public void addRedTeamPlayer(Player player){
        if (!redTeamPlayers.contains(player.getUniqueId())) redTeamPlayers.add(player.getUniqueId());
        removeBlueTeamPlayer(player);
        removeGreenTeamPlayer(player);
        removeYellowTeamPlayer(player);
    }
    public void addBlueTeamPlayer(Player player){
        if (!blueTeamPlayers.contains(player.getUniqueId())) blueTeamPlayers.add(player.getUniqueId());
        removeRedTeamPlayer(player);
        removeYellowTeamPlayer(player);
        removeGreenTeamPlayer(player);
    }
    public void addGreenTeamPlayer(Player player){
        if (!greenTeamPlayers.contains(player.getUniqueId())) greenTeamPlayers.add(player.getUniqueId());
        removeBlueTeamPlayer(player);
        removeYellowTeamPlayer(player);
        removeRedTeamPlayer(player);
    }
    public void addYellowTeamPlayer(Player player){
        if (!yellowTeamPlayers.contains(player.getUniqueId())) yellowTeamPlayers.add(player.getUniqueId());
        removeBlueTeamPlayer(player);
        removeGreenTeamPlayer(player);
        removeRedTeamPlayer(player);
    }

    public void removeRedTeamPlayer(Player player){
        redTeamPlayers.remove(player.getUniqueId());
    }
    public void removeBlueTeamPlayer(Player player){
        blueTeamPlayers.remove(player.getUniqueId());
    }
    public void removeGreenTeamPlayer(Player player){
        greenTeamPlayers.remove(player.getUniqueId());
    }
    public void removeYellowTeamPlayer(Player player){
        yellowTeamPlayers.remove(player.getUniqueId());
    }

    public List<UUID> getRedTeamPlayers(){
        return redTeamPlayers;
    }
    public List<UUID> getBlueTeamPlayers(){
        return blueTeamPlayers;
    }
    public List<UUID> getGreenTeamPlayers(){
        return greenTeamPlayers;
    }
    public List<UUID> getYellowTeamPlayers(){
        return yellowTeamPlayers;
    }
    public void clearTeams(){
        redTeamPlayers.clear();
        blueTeamPlayers.clear();
        greenTeamPlayers.clear();
        yellowTeamPlayers.clear();
    }

    public static TeamManager getInstance() {
        return instance;
    }

}
