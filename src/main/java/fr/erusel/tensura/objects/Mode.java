package fr.erusel.tensura.objects;

import fr.erusel.tensura.enums.Modes;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public abstract class Mode {

    private final String modeName;
    private final Modes mode;
    private final boolean haveTeam;
    private final GScoreboard gScoreboard;

    public Mode(String modeName, Modes mode, GScoreboard gScoreboard, boolean haveTeam) {
        this.modeName = modeName;
        this.mode = mode;
        this.haveTeam = haveTeam;
        this.gScoreboard = gScoreboard;
    }

    public Modes getMode() {
        return mode;
    }
    public String getModeName() {
        return modeName;
    }
    public boolean haveTeam(){
        return haveTeam;
    }
    public GScoreboard getGScoreboard(){
        return gScoreboard;
    }

    public void refreshScoreboard(){
        gScoreboard.refreshPlayingScoreboard();
    }
    public abstract void onPlayerSpawn(Player player);
    public abstract void onStart();
    public abstract void onFinish();
    public abstract void onPlayerJoin(PlayerJoinEvent event);
    public abstract void onPlayerLeave(PlayerQuitEvent event);
    public abstract void onPlayerDeath(PlayerDeathEvent event);
    public abstract void onPlayerRespawn(PlayerRespawnEvent event);
    public abstract void onEntityDamageByEntity(EntityDamageByEntityEvent event);
    public abstract void onPlayerMove(PlayerMoveEvent event);
    public abstract void onBlockBreak(BlockBreakEvent event);
    public abstract void onChat(AsyncPlayerChatEvent event);
    public abstract void onAdvancement(PlayerAdvancementDoneEvent event);
}
