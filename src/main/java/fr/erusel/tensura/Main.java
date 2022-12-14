package fr.erusel.tensura;

import fr.erusel.tensura.commands.*;
import fr.erusel.tensura.listeners.*;
import fr.erusel.tensura.managers.*;
import fr.erusel.tensura.threads.GameLoopRunnable;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;
    private GameManager gameManager;
    private GameSettingManager gameSettingManager;
    private WorldManager worldManager;
    private PlayerManager playerManager;
    private ScoreBoardManager scoreBoardManager;
    private TeamManager teamManager;

    @Override
    public void onEnable() {
        main = this;

        worldManager = new WorldManager();
        playerManager = new PlayerManager();
        gameSettingManager = new GameSettingManager();
        teamManager = new TeamManager();
        gameManager = new GameManager(playerManager, worldManager, teamManager);
        scoreBoardManager = new ScoreBoardManager(gameManager, gameSettingManager, teamManager);

        FastInvManager.register(this);
        saveDefaultConfig();
        registerCommands();
        registerListeners();

        Bukkit.getOnlinePlayers().forEach(player -> {
            scoreBoardManager.initializeScoreboard(player);
            Utils.resetPlayer(player);
        });

        new GameLoopRunnable(gameManager, playerManager, scoreBoardManager).runTaskTimer(this, 0, 20);

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
        getCommand("remove").setExecutor(new RemoveCommand());
    }
    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerJoinQuitListener(gameManager, scoreBoardManager, gameSettingManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new LevelFoodChangeListener(gameManager, playerManager,gameSettingManager), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(gameManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(gameManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(gameManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerAdvancementDoneListener(gameManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(gameManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(gameManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSendChatListener(gameManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(gameManager, playerManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractionListener(gameManager), this);
    }
}
