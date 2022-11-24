package fr.erusel.tensura;

import fr.erusel.tensura.commands.ItemCommand;
import fr.erusel.tensura.commands.SkillCommand;
import fr.erusel.tensura.commands.TensuraCommand;
import fr.erusel.tensura.commands.TensuraTabCompleter;
import fr.erusel.tensura.listeners.PlayerListener;
import fr.erusel.tensura.managers.*;
import fr.erusel.tensura.threads.GameLoopRunnable;
import fr.mrmicky.fastboard.FastBoard;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;
    WorldManager worldManager = new WorldManager();
    ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    GameManager gameManager = new GameManager();
    PlayerManager playerManager = new PlayerManager();

    @Override
    public void onEnable() {
        main = this;
        FastInvManager.register(this);

        registerCommands();
        registerListeners();

        for (Player player : Bukkit.getOnlinePlayers()){
            FastBoard board = new FastBoard(player);
            board.updateTitle("§bTensura §3Game");
            Main.getInstance().getScoreboardManager().scoreboard.put(player.getUniqueId(), board);
        }

        new GameLoopRunnable().runTaskTimer(this, 0, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public WorldManager getWorldManager(){
        return worldManager;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
    public ScoreBoardManager getScoreboardManager() {
        return scoreBoardManager;
    }
    public PlayerManager getPlayerManager(){
        return playerManager;
    }
    public static Main getInstance(){
        return main;
    }
    public void registerCommands(){
        getCommand("tensura").setExecutor(new TensuraCommand());
        getCommand("tensura").setTabCompleter(new TensuraTabCompleter());

        getCommand("skill").setExecutor(new SkillCommand());
        getCommand("item").setExecutor(new ItemCommand());
    }
    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
