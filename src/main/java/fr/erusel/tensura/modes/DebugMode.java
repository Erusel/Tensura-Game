package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.scoreboards.DebugScoreboard;
import fr.erusel.tensura.threads.PlayerLeaveRunnable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Random;

public class DebugMode extends Mode {

    public DebugMode() {
        super("Debug", Modes.DEBUG, new DebugScoreboard(), false);
    }

    @Override
    public void teleportPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!getGameManager().getPlayerList().contains(player.getUniqueId())){
                player.setGameMode(GameMode.SPECTATOR);
            }else {
                getGameManager().addAlivePlayer(player.getUniqueId());
                player.setGameMode(GameMode.SURVIVAL);
            }
            getPlayerManager().createPlayerGPlayer(player);
            player.teleport(getWorldManager().getMap().getSpawnLocation());
            onPlayerSpawn(player);
        });
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
    public void onPlayerLeave(PlayerQuitEvent event) {
        getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()).setLeaveRunnable(new PlayerLeaveRunnable(event.getPlayer(), getGameManager(), getPlayerManager().getGPlayerByUUID(event.getPlayer().getUniqueId()))
                .runTaskTimer(getMain(), 0, 20));
    }
}
