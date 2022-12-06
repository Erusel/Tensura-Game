package fr.erusel.tensura;

import fr.erusel.tensura.commands.SkillCommand;
import fr.erusel.tensura.commands.TeamCommand;
import fr.erusel.tensura.commands.TensuraCommand;
import fr.erusel.tensura.commands.TensuraTabCompleter;
import fr.erusel.tensura.listeners.PlayerListener;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.managers.WorldManager;
import fr.erusel.tensura.threads.GameLoopRunnable;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;
    private GameManager gameManager;
    private WorldManager worldManager;
    private PlayerManager playerManager;
    private ScoreBoardManager scoreBoardManager;

    @Override
    public void onEnable() {
        main = this;

        worldManager = new WorldManager();
        playerManager = new PlayerManager();
        gameManager = new GameManager(playerManager, worldManager);
        scoreBoardManager = new ScoreBoardManager(gameManager);

        FastInvManager.register(this);
        saveDefaultConfig();
        registerCommands();
        registerListeners();

        for (Player player : Bukkit.getOnlinePlayers()){
            scoreBoardManager.initializeScoreboard(player);
        }

        new GameLoopRunnable(gameManager, playerManager, scoreBoardManager).runTaskTimer(this, 0, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance(){
        return main;
    }
    @SuppressWarnings("all")
    public void registerCommands(){
        getCommand("tensura").setExecutor(new TensuraCommand());
        getCommand("tensura").setTabCompleter(new TensuraTabCompleter());

        getCommand("skill").setExecutor(new SkillCommand());
        getCommand("team").setExecutor(new TeamCommand());
        getCommand("join").setExecutor(new JoinCommand());
    }
    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(gameManager, scoreBoardManager, playerManager), this);
    }
}
