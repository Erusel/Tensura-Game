package fr.erusel.tensura.managers;

import fr.erusel.tensura.enums.*;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager {

    private static GameManager instance = null;

    // Server
    private GState gameState = GState.WAITING;
    private UUID gameHost;
    private String hostName = "None";
    private final List<UUID> waitingList = new ArrayList<>();

    // Settings
    private int skillOnStart = 1;
    private int borderRadius = 1000;
    private boolean naturalRegen = false;
    private boolean monsterSpawn = true;
    private boolean raceActivated = true;

    // Game
    private Modes gameMode = Modes.DEBUG;
    private Mode gameModeInstance;
    private final TeamManager teamManager = new TeamManager();
    private final List<Scenarios> activatedScenarios = new ArrayList<>();
    private final List<Scenario> activatedScenariosInstance = new ArrayList<>();
    public int gameStartTime;
    private final List<UUID> playerList = new ArrayList<>();
    private final List<UUID> deadPlayers = new ArrayList<>();
    private final List<UUID> alivePlayers = new ArrayList<>();
    private final List<Skill> uniqueSkillAvailable = new ArrayList<>();

    public GameManager() {
        instance = this;
    }

    public static GameManager getInstance() {
        return instance;
    }

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

        gameModeInstance = gameMode.createInstance();

        setGameState(GState.STARTING);

        // Creating Scenarios Instances
        for (Scenarios scenarios : getActivatedScenarios()) getActivatedScenariosInstance().add(scenarios.createInstance());

        // Creating World
        WorldManager.getInstance().deletePlayingWorld();
        Utils.VoiceOfTheWorldBroadcast("Creating world...");
        WorldManager.getInstance().createPlayingWorld();
        Utils.VoiceOfTheWorldBroadcast("Successful");
        Utils.VoiceOfTheWorldBroadcast("Reincarnation of players");

        // Creating UniqueSkills Instances
        for (Skills skill : Skills.getAllSkillByTier(SkillTier.UNIQUE)){
            uniqueSkillAvailable.add(skill.createInstance());
        }

        // Player resurrection
        for (Player player : Bukkit.getOnlinePlayers()){
            player.setGameMode(GameMode.SURVIVAL);
            playerList.add(player.getUniqueId());
            PlayerManager.getInstance().createPlayerGPlayer(player);
            Utils.resetPlayer(player);
        }
        gameModeInstance.teleportPlayers();
        setGameState(GState.PLAYING);
        gameStartTime = Math.toIntExact(Instant.now().getEpochSecond());
        gameModeInstance.onStart();
        for (Scenario scenario : getActivatedScenariosInstance()) scenario.onStart();
    }
    public void finishGame(UUID uuid){
        Player winner = Bukkit.getPlayer(uuid);
        setGameState(GState.FINISHING);
        for (Player player : Bukkit.getOnlinePlayers()){
            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(Bukkit.getWorld("World").getSpawnLocation());
            Utils.resetPlayer(player);
        }
        Bukkit.broadcastMessage("The winner is " +  winner.getName());
    }
    public List<UUID> getPlayerList(){
        return playerList;
    }
    public Modes getGameMode(){
        return gameMode;
    }
    public void setGameMode(Modes mode){
        this.gameMode = mode;
        gameModeInstance = mode.createInstance();
    }
    public Mode getGameModeInstance(){
        return gameModeInstance;
    }
    public void addWaitingList(UUID uuid){
        if (!waitingList.contains(uuid)) waitingList.add(uuid);
    }
    public void removeWaitingList(UUID uuid){
        waitingList.remove(uuid);
    }
    public List<UUID> getWaitingList(){
        return waitingList;
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

    // Alive Players
    public List<UUID> getAlivePlayers() {
        return alivePlayers;
    }
    public void addAlivePlayer(UUID uuid){
        if (alivePlayers.contains(uuid)) deadPlayers.add(uuid);
    }
    public void removeAlivePlayer(UUID uuid){
        alivePlayers.remove(uuid);
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

    // Game Settings
    public int getSkillOnStart(){
        return skillOnStart;
    }
    public void setSkillOnStart(int i) {
        this.skillOnStart = i;
    }
    public int getMaxPlayer(){
        return (int) Math.floor(Skills.getAllSkillByTier(SkillTier.UNIQUE).size() / getSkillOnStart());
    }
    public boolean getNaturalRegen(){
        return naturalRegen;
    }
    public void setNaturalRegen(boolean b){
        naturalRegen = b;
    }
    public boolean getMonsterSpawn(){
        return monsterSpawn;
    }
    public void setMonsterSpawn(boolean b){
        monsterSpawn = b;
    }
    public boolean isRaceActivated() {
        return raceActivated;
    }
    public void setRaceActivated(boolean raceActivated) {
        this.raceActivated = raceActivated;
    }
    public int getBorderRadius() {
        return borderRadius;
    }
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
}
