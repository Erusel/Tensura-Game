package fr.erusel.tensura.commands;

import fr.erusel.tensura.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player){
            if (!GameManager.getInstance().getGameMode().haveTeam()){
                if (!(GameManager.getInstance().getPlayerList().size() >= GameManager.getInstance().getMaxPlayer())){
                    GameManager.getInstance().getPlayerList().add(player.getUniqueId());
                }else {
                    GameManager.getInstance().addWaitingList(player.getUniqueId());
                    player.sendMessage("§cToo many players, added in waiting list !");
                }
            }else {
                player.sendMessage("Join a team with the /team command");
            }

        }



        return false;
    }
}
