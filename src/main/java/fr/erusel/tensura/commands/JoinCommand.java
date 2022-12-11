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

        if (getGameManager().getGameMode().haveTeam()){
            player.sendMessage("Join a team with the /team command");
        }

        if (getGameManager().getPlayerList().size() > getGameSettingManager().getMaxPlayer()) {
            player.sendMessage("§cToo many players, added in waiting list !");
            getGameManager().addWaitingList(player.getUniqueId());
            return true;
        }

        getGameManager().getPlayerList().add(player.getUniqueId());
        return false;
    }
}
