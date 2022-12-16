package fr.erusel.tensura.commands;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.objects.GameElement;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand extends GameElement implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }
        if (!getGameManager().getGameState().equals(GState.WAITING)){
            return true;
        }
        if (getGameManager().getHostUUID().equals(player.getUniqueId())) {
            if (Bukkit.getPlayer(args[0]) != null) { // pb index oob
                if (getGameManager().getPlayerList().contains(Bukkit.getPlayer(args[0]).getUniqueId())) {
                    getGameManager().getPlayerList().remove(Bukkit.getPlayer(args[0]).getUniqueId());
                    player.sendMessage("§aPlayer removed !");
                    Bukkit.getPlayer(args[0]).sendMessage("§cYou have been removed from the game by the host!");
                    return true;
                }
                player.sendMessage("§cPlayer not in game !");
                return true;
            }
            player.sendMessage("§cPlayer not found !");
            return true;
        }
        if (!getGameManager().getPlayerList().contains(player.getUniqueId())){
            player.sendMessage("§cYou are not in the game !");
            return true;
        }
        if (getGameManager().getWaitingList().contains(player.getUniqueId())){
            player.sendMessage("§cYou left the waiting list !");
            return true;
        }

        getGameManager().getPlayerList().remove(player.getUniqueId());
        player.sendMessage("§aYou left the game !");
        return true;
    }
}
