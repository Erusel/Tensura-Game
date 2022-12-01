package fr.erusel.tensura.managers;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
            for (Player player : Bukkit.getWorld(MAP_NAME).getPlayers()){
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }
            Bukkit.getServer().unloadWorld(MAP_NAME, true);
            File destDir = new File("."+File.separator+MAP_NAME);
            deleteMap(destDir);
        }

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

    private void pasteShematics(File file, int x, int y, int z){
        Clipboard clipboard;

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(map))) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(x, y, z))
                    .build();
            Operations.complete(operation);
        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }

    public World getMap(){
        return map;
    }

    public static WorldManager getInstance() {
        return instance;
    }


}
