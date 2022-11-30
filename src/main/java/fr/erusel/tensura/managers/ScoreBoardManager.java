package fr.erusel.tensura.managers;

import fr.erusel.tensura.Main;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.swing.text.Element;
import java.util.HashMap;
import java.util.UUID;

public class ScoreBoardManager  {

    public final HashMap<UUID, FastBoard> scoreboard = new HashMap<>();

    public void initializeScoreboard(Player player){
        FastBoard board = new FastBoard(player);
        board.updateTitle("§bTensura §3Game");
        Main.getInstance().getScoreboardManager().scoreboard.put(player.getUniqueId(), board);
    }

    public void refreshWaitingScoreboard(){
        HashMap<UUID, FastBoard> scoreboard = Main.getInstance().getScoreboardManager().scoreboard;

        for (FastBoard board: scoreboard.values()){

            board.updateLine(1,"§7------------------");
            board.updateLine(2,"§7Players : " + GameManager.getInstance().getPlayerList().size() + "§3/" + ChatColor.GRAY + GameManager.getInstance().getMaxPlayer());
            board.updateLine(3, "§7Host : §6" + GameManager.getInstance().getHostName());
            board.updateLine(4, "§7Mode : §6" + GameManager.getInstance().getGameMode().getModeName());
            if (GameManager.getInstance().getGameMode().haveTeam()){
                board.updateLine(5, "§7Team : §6" + GameManager.getInstance().getTeamManager().getPlayerTeam(board.getPlayer().getUniqueId()));
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
