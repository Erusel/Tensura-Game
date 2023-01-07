package fr.erusel.tensura.managers;

import fr.erusel.tensura.enums.*;
import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Scenario;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager {

    private static GameManager instance;
    private final PlayerManager playerManager;
    private final WorldManager worldManager;
    private final TeamManager teamManager;

    // Server
    private GState gameState = GState.WAITING;
    private UUID gameHost = UUID.randomUUID();
    private String hostName = "None";
    private final List<UUID> waitingList = new ArrayList<>();

    // Game
    private Modes gameMode = Modes.DEBUG;
    private Mode gameModeInstance;
    private final List<Scenarios> activatedScenarios = new ArrayList<>();
    private final List<Scenario> activatedScenariosInstance = new ArrayList<>();
    public int gameStartTime;
    private final List<UUID> playerList = new ArrayList<>();
    private final List<UUID> deadPlayers = new ArrayList<>();
    private final List<Skill> uniqueSkillAvailable = new ArrayList<>();
    private final List<Location> crateLocations = new ArrayList<>();

    public GameManager(PlayerManager playerManager, WorldManager worldManager, TeamManager teamManager) {
        instance = this;
        this.playerManager = playerManager;
        this.worldManager = worldManager;
        this.teamManager = teamManager;
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
        if (!worldManager.isPlayingMapExist()){
            p.sendMessage("§cMap not found, pregen one with /tensura pregen");
            return;

        }
        setGameState(GState.STARTING);
        gameModeInstance = gameMode.createInstance();


        // Creating Scenarios Instances
        getActivatedScenarios().
                forEach(scenarios -> getActivatedScenariosInstance().add(scenarios.createInstance()));

        // Creating UniqueSkills Instances
        Skills.getAllSkillByTier(SkillTier.UNIQUE).forEach(
                skill -> getUniqueSkillAvailable().add(skill.createInstance())
        );

        gameModeInstance.teleportPlayers();
        gameStartTime = Math.toIntExact(Instant.now().getEpochSecond());
        gameModeInstance.onStart();

        getActivatedScenariosInstance().stream()
                .filter(s -> s instanceof Eventable)
                .forEach(scenario -> ((Eventable) scenario).onStart());

        setGameState(GState.PLAYING);
    }

    public void finishGame(UUID uuid){
        Player winner = Bukkit.getPlayer(uuid);
        setGameState(GState.FINISHING);
        Bukkit.getOnlinePlayers().forEach(player -> {
            playerManager.removePlayerGPlayer(player);
            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(Bukkit.getWorld("World").getSpawnLocation());
            Utils.resetPlayer(player);
        });
        teamManager.clearTeams();
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

    // Dead Players
    public List<UUID> getDeadPlayers(){
        return deadPlayers;
    }
    public void addDeadPlayer(UUID uuid){
        deadPlayers.add(uuid);
    }
    public void removeDeadPlayers(UUID uuid) {
        deadPlayers.remove(uuid);
    }

    // Crates
    public List<Location> getCrateLocations(){
        return crateLocations;
    }
    public void addCrateLocation(Location location){
        crateLocations.add(location);
    }
    public void removeCrateLocation(Location location) {
        crateLocations.remove(location);
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
