package fr.erusel.tensura.scoreboards;

import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.GPlayer;
import fr.erusel.tensura.objects.GScoreboard;
import fr.erusel.tensura.utils.Utils;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

public class DebugScoreboard extends GScoreboard {

    @Override
    public void refreshPlayingScoreboard() {

        HashMap<UUID, FastBoard> scoreboard = ScoreBoardManager.getInstance().scoreboard;
        int time = Math.toIntExact(Instant.now().getEpochSecond());

        for (UUID uuid : scoreboard.keySet()) {

            FastBoard board = scoreboard.get(uuid);
            GPlayer gPlayer = PlayerManager.getInstance().getGPlayerByUUID(uuid);

            board.updateLine(1, "§7------------------");
            board.updateLine(2,"§7Players : " + GameManager.getInstance().getPlayerList().size() + "§3/" + ChatColor.GRAY + GameManager.getInstance().getMaxPlayer());
            board.updateLine(3, "§7Host : §6" + GameManager.getInstance().getHostName());
            board.updateLine(4, "§7Mode : §6" + GameManager.getInstance().getGameMode().getModeName());
            board.updateLine(5, "§7Kills : §c" + gPlayer.getKills());
            board.updateLine(6, "§7------------------");
            board.updateLine(7, "§7Time : " + Utils.getTime(time - GameManager.getInstance().gameStartTime));
            board.updateLine(8, "§7------------------");
            if (GameManager.getInstance().isRaceActivated()){
                board.updateLine(9, "§7Race : §a" + gPlayer.getRace().getName());
                board.updateLine(10, "§7------------------");
                board.updateLine(11, "§3By Erusel");
            }else {
                board.updateLine(9, "§3By Erusel");
            }

        }
    }
}
