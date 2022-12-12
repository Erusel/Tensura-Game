package fr.erusel.tensura.commands;

import fr.erusel.tensura.objects.GameElement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand extends GameElement implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }
        if (getGameManager().getPlayerList().contains(player.getUniqueId())){
            player.sendMessage("§cYou are already in the game !");
            return true;
        }
        if (getGameManager().getGameMode().haveTeam()){
            player.sendMessage("Join a team with the /team command");
            return true;
        }
        if (getGameManager().getPlayerList().size() > getGameSettingManager().getMaxPlayer()) {
            if (getGameManager().getWaitingList().contains(player.getUniqueId())){
                player.sendMessage("§cYou are already in the waiting list !");
                return true;
            }
            player.sendMessage("§cToo many players, added in waiting list !");
            getGameManager().addWaitingList(player.getUniqueId());
            return true;
        }

        getGameManager().getPlayerList().add(player.getUniqueId());
        return true;
    }
}
