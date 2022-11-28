package fr.erusel.tensura.managers;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class WorldManager {

    private final String MAP_NAME = "map";
    private World map;

    public void createPlayingWorld(){
        WorldCreator worldCreator = new WorldCreator(MAP_NAME);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.NORMAL);
        map = worldCreator.createWorld();
        map.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        map.setGameRule(GameRule.DISABLE_RAIDS, true);
        map.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        map.setGameRule(GameRule.DO_INSOMNIA, false);
        map.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
    }

    public void deletePlayingWorld(){
        if (Bukkit.getWorld(MAP_NAME) != null){
            for (Player player : Bukkit.getWorld(MAP_NAME).getPlayers()){
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }
            Bukkit.getServer().unloadWorld(MAP_NAME, true);
            File destDir = new File("."+File.separator+MAP_NAME);
            deleteMap(destDir);
        }

    }

    public void teleportPlayerToMap(Player player){
        player.teleport(Bukkit.getWorld(MAP_NAME).getSpawnLocation());
    }

    private void deleteMap(File dir) {
        File[] files = dir.listFiles();
        for(File d : files){
            if(d.isDirectory()){
                deleteMap(d);
            }
            d.delete();
        }
        dir.delete();
    }

    public World getMap(){
        return map;
    }


}
