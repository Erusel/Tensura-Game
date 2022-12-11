package fr.erusel.tensura.modes;

import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.scoreboards.BattleRoyalScoreboard;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class BattleRoyalMode extends Mode {

    Random random = new Random();

    public BattleRoyalMode() {
        super("Battle Royal", Modes.BATTLE_ROYAL, new BattleRoyalScoreboard(), false);
    }

    @Override
    public void teleportPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()){
            //spawn player at random location in the map
            int x = random.nextInt(getGameSettingManager().getBorderRadius())-500;
            int z = random.nextInt(getGameSettingManager().getBorderRadius())-500;
            player.teleport(getWorldManager().getMap().getHighestBlockAt(x, z).getLocation());
            onPlayerSpawn(player);
        }
    }

    @Override
    public void onPlayerSpawn(Player player) {

        // Give Skills
        for (int z = 0; z < getGameSettingManager().getSkillOnStart(); z++) {
            int i = random.nextInt(getGameManager().getUniqueSkillAvailable().size());
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
        Bukkit.broadcastMessage("§5Battle Royal");
        // Change the world border size to 20000 blocks max
        getWorldManager().getMap().getWorldBorder().setSize(20000);
    }

    @Override
    public void onFinish() {
        Utils.VoiceOfTheWorldBroadcast("GAME ENDED, 1 ALIVE PLAYER");
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (getGameManager().getAlivePlayers().size() == 1){
            onFinish();
        }
    }

}
