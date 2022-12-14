package fr.erusel.tensura.managers;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ScoreBoardManager {

    private static ScoreBoardManager instance;
    private final GameManager gameManager;
    private final GameSettingManager gameSettingManager;
    private final TeamManager teamManager;

    public final HashMap<UUID, FastBoard> scoreboard = new HashMap<>();

    public ScoreBoardManager(GameManager gameManager, GameSettingManager gameSettingManager, TeamManager teamManager) {
        instance = this;
        this.gameManager = gameManager;
        this.gameSettingManager = gameSettingManager;
        this.teamManager = teamManager;
    }

    public static ScoreBoardManager getInstance() {
        return instance;
    }

    public void initializeScoreboard(Player player){
        FastBoard board = new FastBoard(player);
        board.updateTitle("§bTensura §3Game");
        scoreboard.put(player.getUniqueId(), board);
    }

    public void refreshWaitingScoreboard(){

        int playerAlive = gameManager.getPlayerList().size() - gameManager.getDeadPlayers().size();

        for (FastBoard board: scoreboard.values()){

            board.updateLine(1,"§7§m------------------");
            board.updateLine(2,"§7Players : " + playerAlive + "§3/" + ChatColor.GRAY + gameSettingManager.getMaxPlayer());
            board.updateLine(3, "§7Host : §6" + gameManager.getHostName());
            board.updateLine(4, "§7Mode : §6" + gameManager.getGameMode().getModeName());
            if (gameManager.getGameMode().haveTeam()){
                board.updateLine(5, "§7Team : §6" + teamManager.getPlayerTeam(board.getPlayer().getUniqueId()));
                board.updateLine(6,"§7§m------------------");
                board.updateLine(7, "§3By Erusel");
            } else {
                board.updateLine(5,"§7§m------------------");
                board.updateLine(6, "§3By Erusel");
                board.removeLine(7);
            }

        }
    }

}
