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
        if (redTeamPlayers.contains(uuid)) {
            return "Red";
        }
        if (blueTeamPlayers.contains(uuid)) {
            return "Blue";
        }
        if (greenTeamPlayers.contains(uuid)) {
            return "Green";
        }
        if (yellowTeamPlayers.contains(uuid)) {
            return "Yellow";
        }
        return "None";
    }


    public void addRedTeamPlayer(Player player){
        clearPlayerTeam(player);
        if (!redTeamPlayers.contains(player.getUniqueId())) {
            redTeamPlayers.add(player.getUniqueId());
        }
    }
    public void addBlueTeamPlayer(Player player){
        clearPlayerTeam(player);
        if (!blueTeamPlayers.contains(player.getUniqueId())) {
            blueTeamPlayers.add(player.getUniqueId());
        }
    }
    public void addGreenTeamPlayer(Player player){
        clearPlayerTeam(player);
        if (!greenTeamPlayers.contains(player.getUniqueId())) {
            greenTeamPlayers.add(player.getUniqueId());
        }
    }
    public void addYellowTeamPlayer(Player player){
        clearPlayerTeam(player);
        if (!yellowTeamPlayers.contains(player.getUniqueId())) {
            yellowTeamPlayers.add(player.getUniqueId());
        }
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

    public void clearPlayerTeam(Player player){
        removeRedTeamPlayer(player);
        removeBlueTeamPlayer(player);
        removeGreenTeamPlayer(player);
        removeYellowTeamPlayer(player);
    }

    public static TeamManager getInstance() {
        return instance;
    }

}
