package fr.erusel.tssdkuhc.managers;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.GState;
import fr.erusel.tssdkuhc.enums.RaceStages;
import fr.erusel.tssdkuhc.enums.Races;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.objects.Race;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager {

    private GState gameState = GState.WAITING;
    private UUID gameHost;
    private String hostName = "Aucun";
    public int gameStartTime;
    private final List<UUID> playerList = new ArrayList<>();

    public void startGame(){
        if (!gameHasHost()){
            return;
        }
        setGameState(GState.STARTING);
        Main.getInstance().getWorldManager().deletePlayingWorld();
        Bukkit.broadcastMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Creating world");
        Main.getInstance().getWorldManager().createPlayingWorld();
        Bukkit.broadcastMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Successful");
        Bukkit.broadcastMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Reincarnation of players");

        // Player resurrection
        for (Player player : Bukkit.getOnlinePlayers()){
            playerList.add(player.getUniqueId());
            Main.getInstance().getPlayerManager().createPlayerGPlayer(player);
            Race race;
            try {
                race = (Race) Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).getRaceClass().getConstructor().newInstance();
                Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill((Skill) Skills.GREATSAGE.getSkillClass().getConstructor().newInstance());
                Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill((Skill) Skills.SPEEDY.getSkillClass().getConstructor().newInstance());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
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


}
