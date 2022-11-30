package fr.erusel.tensura.modes;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.scoreboards.BattleRoyalScoreboard;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Random;

public class BattleRoyalMode extends Mode {
    public BattleRoyalMode() {
        super("Battle Royal", Modes.BATTLE_ROYAL, new BattleRoyalScoreboard(), false);
    }

    @Override
    public void onPlayerSpawn(Player player) {

        // Give Skills
        for (int z = 0; z < GameManager.getInstance().getSkillOnStart(); z++) {
            int i = new Random().nextInt(GameManager.getInstance().getUniqueSkillAvailable().size());
            Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(GameManager.getInstance().getUniqueSkillAvailable().get(i));
            GameManager.getInstance().getUniqueSkillAvailable().remove(GameManager.getInstance().getUniqueSkillAvailable().get(i));
        }

        // Give Races
        if (GameManager.getInstance().isRaceActivated()){
            Race race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).createInstance();
            Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
            Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);
            player.sendMessage("§aYou have been resurrected as a " + race.getName());
        }
    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§5Battle Royal");
    }

    @Override
    public void onFinish() {
        Utils.VoiceOfTheWorldBroadcast("GAME ENDED, 1 ALIVE PLAYER");
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @Override
    public void onPlayerLeave(PlayerQuitEvent event) {

    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (GameManager.getInstance().getAlivePlayers().size() == 1){
            onFinish();
        }
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
