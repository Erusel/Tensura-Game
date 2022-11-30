package fr.erusel.tensura.managers;

import fr.erusel.tensura.Main;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;

import javax.swing.text.Element;
import java.util.HashMap;
import java.util.UUID;

public class ScoreBoardManager  {

    public final HashMap<UUID, FastBoard> scoreboard = new HashMap<>();

    public void refreshWaitingScoreboard(){
        HashMap<UUID, FastBoard> scoreboard = Main.getInstance().getScoreboardManager().scoreboard;

        for (FastBoard board: scoreboard.values()){

            board.updateLine(1,"§7------------------");
            board.updateLine(2,"§7Players : " + Main.getInstance().getGameManager().getPlayerList().size() + "§3/" + ChatColor.GRAY + Main.getInstance().getGameManager().getMaxPlayer());
            board.updateLine(3, "§7Host : §6" + Main.getInstance().getGameManager().getHostName());
            board.updateLine(4, "§7Mode : §6" + Main.getInstance().getGameManager().getGameMode().getModeName());
            if (Main.getInstance().getGameManager().getGameMode().haveTeam()){
                board.updateLine(5, "§7Team : §6" + Main.getInstance().getGameManager().getTeamManager().getPlayerTeam(board.getPlayer().getUniqueId()));
                board.updateLine(6,"§7------------------");
                board.updateLine(7, "§3By Erusel");
            } else {
                board.updateLine(5,"§7------------------");
                board.updateLine(6, "§3By Erusel");
                board.removeLine(7);
            }

        }
    }

}
