package fr.erusel.tensura.managers;

import org.bukkit.*;

import java.io.File;
import java.util.Arrays;

public class WorldManager {

    private static WorldManager instance;

    private final String MAP_NAME = "map";
    private World map;

    public WorldManager() {
        instance = this;
    }

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
        map.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
    }

    public void deletePlayingWorld(){
        if (Bukkit.getWorld(MAP_NAME) != null){
            Bukkit.getWorld(MAP_NAME).getPlayers()
                    .forEach(p -> p.teleport(Bukkit.getWorld("world").getSpawnLocation()));
            Bukkit.getServer().unloadWorld(MAP_NAME, true);
            File destDir = new File("."+File.separator+MAP_NAME);
            deleteMap(destDir);
        }

    }

    private void deleteMap(File dir) {
        File[] files = dir.listFiles();
        Arrays.stream(files)
                .forEach(file -> {
                    if(file.isDirectory()){
                        deleteMap(file);
                    }
                    file.delete();
                });
        dir.delete();
    }

    public World getMap(){
        return map;
    }

    public static WorldManager getInstance() {
        return instance;
    }


}
