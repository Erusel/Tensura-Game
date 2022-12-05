package fr.erusel.tensura.commands;

import fr.erusel.tensura.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    GameManager gameManager;

    public JoinCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player){
            if (!gameManager.getGameMode().haveTeam()){
                if (!(gameManager.getPlayerList().size() >= gameManager.getMaxPlayer())){
                    gameManager.getPlayerList().add(player.getUniqueId());
                }else {
                    gameManager.addWaitingList(player.getUniqueId());
                    player.sendMessage("§cToo many players, added in waiting list !");
                }
            }else {
                player.sendMessage("Join a team with the /team command");
            }

        }



        return false;
    }
}
