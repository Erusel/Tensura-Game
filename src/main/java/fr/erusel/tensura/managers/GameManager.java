package fr.erusel.tensura.managers;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.*;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager {

    // Server
    private GState gameState = GState.WAITING;
    private UUID gameHost;
    private String hostName = "None";


    // Game
    private Modes gameMode = Modes.DEBUG;
    private Mode gameModeInstance;
    private final TeamManager teamManager = new TeamManager();
    private final List<Scenarios> activatedScenarios = new ArrayList<>();
    private final List<Scenario> activatedScenariosInstance = new ArrayList<>();
    public int gameStartTime;
    private final List<UUID> playerList = new ArrayList<>();
    private final List<UUID> deadPlayers = new ArrayList<>();
    private final List<Skill> uniqueSkillAvailable = new ArrayList<>();








    // Game methode
    public void startGame(Player p){
        if (!gameHasHost()){
            p.sendMessage("§cNo host selected !");
            return;
        }
        if (gameMode.equals(Modes.NONE)) {
            p.sendMessage("§cPlease choose a gamemode !");
            return;
        }
        setGameState(GState.STARTING);

        // Creating games Instances
        gameModeInstance = gameMode.createInstance();

        // Creating Scenarios Instances
        for (Scenarios scenarios : getActivatedScenarios()) getActivatedScenariosInstance().add(scenarios.createInstance());

        // Creating UniqueSkills Instances
        for (Skills skill : Skills.getAllSkillByTier(SkillTier.UNIQUE)){
            try {
                uniqueSkillAvailable.add(skill.getSkillClass().getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        // Creating World
        Main.getInstance().getWorldManager().deletePlayingWorld();
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Creating world...");
        Main.getInstance().getWorldManager().createPlayingWorld();
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Successful");
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Reincarnation of players");

        // Player resurrection
        for (Player player : Bukkit.getOnlinePlayers()){
            playerList.add(player.getUniqueId());
            Main.getInstance().getPlayerManager().createPlayerGPlayer(player);
            gameModeInstance.onPlayerSpawn(player);
            Main.getInstance().getWorldManager().teleportPlayerToMap(player);
        }
        gameModeInstance.onStart();
        setGameState(GState.PLAYING);
        gameStartTime = Math.toIntExact(Instant.now().getEpochSecond());
    }
    public void finishGame(){

    }

    public List<UUID> getPlayerList(){
        return playerList;
    }
    public Modes getGameMode(){
        return gameMode;
    }
    public void setGameMode(Modes mode){
        this.gameMode = mode;
    }
    public Mode getGameModeInstance(){
        return gameModeInstance;
    }

    // GameState
    public void setGameState(GState gameState){
        this.gameState = gameState;
    }
    public GState getGameState(){
        return gameState;
    }


    // Game Host
    public void setPlayerHost(Player player){
        this.gameHost = player.getUniqueId();
        this.hostName = player.getName();
    }
    public boolean gameHasHost(){
        return gameHost != null;
    }
    public UUID getHostUUID(){
        return gameHost;
    }
    public boolean playerIsHost(Player player){
        return player.getUniqueId().equals(getHostUUID());
    }
    public String getHostName(){
        return hostName;
    }

    // Dead Players
    public List<UUID> getDeadPlayers(){
        return deadPlayers;
    }
    public void addDeadPlayer(UUID uuid){
        deadPlayers.add(uuid);
    }
    public void removeDeadPlayers(UUID uuid){
        deadPlayers.remove(uuid);
    }

    // Team Manager
    public TeamManager getTeamManager(){
        return teamManager;
    }

    // Unique Skills
    public List<Skill> getUniqueSkillAvailable(){
        return uniqueSkillAvailable;
    }

    // Scenarios
    public List<Scenarios> getActivatedScenarios() {
        return activatedScenarios;
    }
    public List<Scenario> getActivatedScenariosInstance() {
        return activatedScenariosInstance;
    }
}
