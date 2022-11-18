package fr.erusel.tensura.managers;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.objects.GPlayer;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class ScoreBoardManager {

    public final HashMap<UUID, FastBoard> scoreboard = new HashMap<>();

    public void refreshWaitingScoreboard(){

        for (FastBoard board: scoreboard.values()){

            board.updateLine(1,"§7----------------------");
            board.updateLine(2,"§7Players : " + Bukkit.getOnlinePlayers().size() + "§3/" + ChatColor.GRAY + Bukkit.getMaxPlayers());
            board.updateLine(3, "§7Host : §6" + Main.getInstance().getGameManager().getHostName());
            board.updateLine(6,"§7----------------------");
            board.updateLine(7, "§3By Erusel");

        }
    }

    public void refreshPlayingScoreboard() {

        int time = Math.toIntExact(Instant.now().getEpochSecond());

        for (UUID uuid : scoreboard.keySet()) {

            FastBoard board = scoreboard.get(uuid);
            GPlayer gPlayer = Main.getInstance().getPlayerManager().getGPlayerByUUID(uuid);

            board.updateLine(1, "§7----------------------");
            board.updateLine(2,"§7Players : " + Bukkit.getOnlinePlayers().size() + "§3/" + ChatColor.GRAY + Bukkit.getMaxPlayers());
            board.updateLine(3, "§7Host : §6" + Main.getInstance().getGameManager().getHostName());
            board.updateLine(4, "§7Kills : §c" + gPlayer.getKills());
            board.updateLine(5, "§7----------------------");
            board.updateLine(6, "§7Time : " + getTime(time - Main.getInstance().getGameManager().gameStartTime));
            board.updateLine(7, "§7----------------------");
            board.updateLine(8, "§7Race : §a" + gPlayer.getRace().getName());
            board.updateLine(9, "§7----------------------");
            board.updateLine(10, "§3By Erusel");

        }


    }

    public static String getTime(Integer secs) {
        return String.format("§7%02d§7:§7%02d§7:§7%02d", ((secs / 3600) % 24), (secs % 3600) / 60, secs % 60);
    }


}