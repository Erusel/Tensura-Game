package fr.erusel.tensura.scoreboards;

import fr.erusel.tensura.managers.ScoreBoardManager;
import fr.erusel.tensura.objects.GScoreboard;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.UUID;

public class CharybdisScoreboard extends GScoreboard {
    @Override
    public void refreshPlayingScoreboard() {
        HashMap<UUID, FastBoard> scoreboard = ScoreBoardManager.getInstance().scoreboard;

        for (FastBoard board: scoreboard.values()){

            board.updateLine(1,"§7------------------");
            board.updateLine(2,"§7Players : " + getGameManager().getPlayerList().size() + "§3/" + ChatColor.GRAY + getGameManager().getMaxPlayer());
            board.updateLine(3, "§7Host : §6" + getGameManager().getHostName());
            board.updateLine(4, "§7Mode : §6" + getGameManager().getGameMode().getModeName());
            board.updateLine(5, "§7Team : §6" + getGameManager().getTeamManager().getPlayerTeam(board.getPlayer().getUniqueId()));
            board.updateLine(6,"§7------------------");
            board.updateLine(7, "§3By Erusel");
        }
    }

}
