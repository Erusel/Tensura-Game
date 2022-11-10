package fr.erusel.tssdkuhc;

import fr.erusel.tssdkuhc.commands.SkillCommand;
import fr.erusel.tssdkuhc.commands.TensuraCommand;
import fr.erusel.tssdkuhc.commands.TensuraTabCompleter;
import fr.erusel.tssdkuhc.enums.GState;
import fr.erusel.tssdkuhc.listeners.PlayerListener;
import fr.erusel.tssdkuhc.managers.GameManager;
import fr.erusel.tssdkuhc.managers.PlayerManager;
import fr.erusel.tssdkuhc.managers.ScoreBoardManager;
import fr.erusel.tssdkuhc.managers.WorldManager;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.PassiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
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

    public static final String VOICE_OF_THE_WORLD_PREFIX = "§6§b[§r§6§nVoice of the World§6§b]§r§o§9 ";
    @Override
    public void onEnable() {
        main = this;
        FastInvManager.register(this);

        getCommand("tensura").setExecutor(new TensuraCommand());
        getCommand("tensura").setTabCompleter(new TensuraTabCompleter());
        getCommand("skill").setExecutor(new SkillCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            if (gameManager.getGameState().equals(GState.WAITING))  scoreBoardManager.refreshWaitingScoreboard();
            if (gameManager.getGameState().equals(GState.PLAYING)){
                scoreBoardManager.refreshPlayingScoreboard();
                for (Player player : Bukkit.getOnlinePlayers()){
                    if (gameManager.getPlayerList().contains(player.getUniqueId())){
                        for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getPlayerSkills()){
                            if (skill instanceof PassiveSkill){
                                ((PassiveSkill) skill).eachSecond(player);
                            }else if (skill instanceof ActiveSkill){
                                if (skill.inCooldown()){
                                    skill.setCurrentCooldown(skill.getCurrentCooldown()-1);
                                }
                            }
                        }
                    }
                }
            }
            }, 0, 20);
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
}
