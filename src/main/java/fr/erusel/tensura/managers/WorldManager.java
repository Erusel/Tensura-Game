package fr.erusel.tensura.managers;

import fr.erusel.tensura.chunkgenerators.EmptyChunkGenerator;
import org.bukkit.*;
import org.bukkit.block.Chest;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class WorldManager {

    private final GameManager gameManager;
    private final GameSettingManager gameSettingManager;

    private static WorldManager instance;

    private final String MAP_NAME = "map";
    private World map = null;
    private final Random random = new Random();

    public WorldManager(GameManager gameManager, GameSettingManager gameSettingManager) {
        instance = this;
        this.map = Bukkit.getWorld(MAP_NAME);
        this.gameManager = gameManager;
        this.gameSettingManager = gameSettingManager;
    }
    public boolean isPlayingMapExist() {
        return this.map != null;
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
        // Désactivé sinon NPE car gameManager.getGameMode() est null
        //if (gameManager.getGameMode().equals(Modes.BATTLE_ROYAL)) {
        //    Utils.VoiceOfTheWorldBroadcast("Placing lootcrates...");
        //    placeChestBattleRoyal();
        //}
    }
    public void createPersonalDimension() {
        WorldCreator worldCreator = new WorldCreator("personal_dimension");
        worldCreator.generator(new EmptyChunkGenerator());
        worldCreator.type(WorldType.FLAT);
        worldCreator.generateStructures(false);
        worldCreator.createWorld();
    }
    public void placeChestBattleRoyal() {
        for (int i = 0; i < gameSettingManager.getAmountCrates(); i++) {
            int x = random.nextInt(gameSettingManager.getBorderRadius())-500;
            int z = random.nextInt(gameSettingManager.getBorderRadius())-500;
            Location loc = this.getMap().getHighestBlockAt(x, z).getLocation();
            this.getMap().getBlockAt(loc).setType(Material.CHEST);
            Chest chest = (Chest) loc.getBlock().getState();
            gameManager.addCrateLocation(chest.getLocation());
        }
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
