package fr.erusel.tensura.modes;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Modes;
import fr.erusel.tensura.enums.RaceStages;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.objects.Mode;
import fr.erusel.tensura.objects.Race;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Random;

public class DebugMode extends Mode {

    public DebugMode() {
        super("Debug", Modes.DEBUG, false);
    }

    @Override
    public void onPlayerSpawn(Player player) {
        Race race = Races.getRandomRaceByStage(RaceStages.FIRSTSTAGE).createInstance();
        int i = new Random().nextInt(Main.getInstance().getGameManager().getUniqueSkillAvailable().size());
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).addSkill(Main.getInstance().getGameManager().getUniqueSkillAvailable().get(i));
        Main.getInstance().getGameManager().getUniqueSkillAvailable().remove(Main.getInstance().getGameManager().getUniqueSkillAvailable().get(i));

        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).setRace(race);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).getRace().onGive(player);

        player.sendMessage("§aYou have been resurrected as a " + race.getName());
    }

    @Override
    public void onStart() {
        Bukkit.broadcastMessage("§c DEBUG MODE | ONLY FOR DEVELOPMENT !");
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
