package fr.erusel.tensura.managers;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.*;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GameManager {

    private GState gameState = GState.WAITING;
    private UUID gameHost;
    private String hostName = "Aucun";
    public int gameStartTime;
    private final List<UUID> playerList = new ArrayList<>();
    private final List<UUID> deadPlayers = new ArrayList<>();
    private final List<Skill> uniqueSkillAvailable = new ArrayList<>();

    public void startGame(){
        if (!gameHasHost()){
            return;
        }
        for (Skills skill : Skills.getAllSkillByTier(SkillTier.UNIQUE)){
            try {
                uniqueSkillAvailable.add(skill.getSkillClass().getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        setGameState(GState.STARTING);
        Main.getInstance().getWorldManager().deletePlayingWorld();
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Creating world...");
        Main.getInstance().getWorldManager().createPlayingWorld();
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Successful");
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Reincarnation of players");

        // Player resurrection
        for (Player player : Bukkit.getOnlinePlayers()){
            playerList.add(player.getUniqueId());
            Main.getInstance().getPlayerManager().createPlayerGPlayer(player);
            Race race;
            try {
                race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).getRaceClass().getConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            int i = new Random().nextInt(uniqueSkillAvailable.size());
            Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(uniqueSkillAvailable.get(i));
            uniqueSkillAvailable.remove(uniqueSkillAvailable.get(i));

            Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
            Main.getInstance().getWorldManager().teleportPlayerToMap(player);
            Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);

            player.sendMessage("§aYou have been resurrected as a " + race.getName());
        }

        setGameState(GState.PLAYING);
        gameStartTime = Math.toIntExact(Instant.now().getEpochSecond());
    }

    public List<UUID> getPlayerList(){
        return playerList;
    }

    public void setGameState(GState gameState){
        this.gameState = gameState;
    }

    public GState getGameState(){
        return gameState;
    }

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

    public String getHostName(){
        return hostName;
    }

    public List<UUID> getDeadPlayers(){
        return deadPlayers;
    }

    public void addDeadPlayer(UUID uuid){
        deadPlayers.add(uuid);
    }

    public void removeDeadPlayers(UUID uuid){
        deadPlayers.remove(uuid);
    }

}
