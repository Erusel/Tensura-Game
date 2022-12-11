package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.scoreboards.DebugScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Random;

public class DebugMode extends Mode {

    public DebugMode() {
        super("Debug", Modes.DEBUG, new DebugScoreboard(), false);
    }

    @Override
    public void teleportPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()){
            player.teleport(getWorldManager().getMap().getSpawnLocation());
            onPlayerSpawn(player);
        }
    }

    @Override
    public void onPlayerSpawn(Player player) {

        // Give Skill
        for (int z = 0; z < getGameSettingManager().getSkillOnStart(); z++) {
            int i = new Random().nextInt(getGameManager().getUniqueSkillAvailable().size());
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(getGameManager().getUniqueSkillAvailable().get(i));
            getGameManager().getUniqueSkillAvailable().remove(getGameManager().getUniqueSkillAvailable().get(i));
        }

        // Give Races
        if (getGameSettingManager().isRaceActivated()){
            Race race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).createInstance();
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
            getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);
            player.sendMessage("§aYou have been resurrected as a " + race.getName());
        }
    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§c DEBUG MODE | ONLY FOR DEVELOPMENT !");
        getWorldManager().getMap().setGameRule(GameRule.NATURAL_REGENERATION, getGameSettingManager().getNaturalRegen());
        getWorldManager().getMap().setGameRule(GameRule.DO_MOB_SPAWNING, getGameSettingManager().getMonsterSpawn());
        getWorldManager().getMap().getWorldBorder().setSize(getGameSettingManager().getBorderRadius());
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent event) {

    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {

    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {

    }

    @Override
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {

    }

    @Override
    public void onChat(AsyncPlayerChatEvent event) {

    }

    @Override
    public void onAdvancement(PlayerAdvancementDoneEvent event) {

    }
}
