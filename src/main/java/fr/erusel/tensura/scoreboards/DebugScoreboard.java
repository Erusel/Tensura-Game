package fr.erusel.tensura.scoreboards;

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

        HashMap<UUID, FastBoard> scoreboard = getScoreBoardManager().scoreboard;
        int time = Math.toIntExact(Instant.now().getEpochSecond());
        for (UUID uuid : scoreboard.keySet()) {

            FastBoard board = scoreboard.get(uuid);
            GPlayer gPlayer = getPlayerManager().getGPlayerByUUID(uuid);

            board.updateLine(1, "§7§m------------------");
            board.updateLine(2,"§7Players : " + getGameManager().getPlayerList().size() + "§3/" + ChatColor.GRAY + getGameSettingManager().getMaxPlayer());
            board.updateLine(3, "§7Host : §6" + getGameManager().getHostName());
            board.updateLine(4, "§7Mode : §6" + getGameManager().getGameMode().getModeName());
            board.updateLine(5, "§7Kills : §c" + gPlayer.getKills());
            board.updateLine(6, "§7§m------------------");
            board.updateLine(7, "§7Time : " + Utils.getTime(time - getGameManager().gameStartTime));
            board.updateLine(8, "§7§m------------------");
            if (getGameSettingManager().isRaceActivated()){
                String raceName = "Spectator";
                if (gPlayer.getRace() != null){
                    raceName = gPlayer.getRaces().getName();
                }
                board.updateLine(9, "§7Race : §a" + raceName);
                board.updateLine(10, "§7§m------------------");
                board.updateLine(11, "§3By Erusel");
            } else {
                board.updateLine(9, "§3By Erusel");
            }

        }
    }
}
