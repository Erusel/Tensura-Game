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
    private final WorldManager worldManager = new WorldManager();
    private final ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    private GameManager gameManager;
    private final PlayerManager playerManager = new PlayerManager();

    @Override
    public void onEnable() {
        main = this;
        gameManager = new GameManager();
        FastInvManager.register(this);
        saveDefaultConfig();
        registerCommands();
        registerListeners();

        for (Player player : Bukkit.getOnlinePlayers()){
            scoreBoardManager.initializeScoreboard(player);

            if (!(gameManager.getPlayerList().size() >= gameManager.getMaxPlayer())){
                gameManager.getPlayerList().add(player.getUniqueId());
            }else {
                gameManager.addWaitingList(player.getUniqueId());
                player.sendMessage("§cToo many players, added in waiting list !");
            }
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
    public ScoreBoardManager getScoreboardManager() {
        return scoreBoardManager;
    }
    public PlayerManager getPlayerManager(){
        return playerManager;
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
    }
    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
