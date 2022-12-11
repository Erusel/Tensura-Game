package fr.erusel.tensura.commands;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.inventories.TeamChooseGUI;
import fr.erusel.tensura.objects.GameElement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand extends GameElement implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!getGameManager().getGameState().equals(GState.WAITING)) {
            return true;
        }

        if (getGameManager().getGameMode().haveTeam()) {
            player.sendMessage("This gamemode have no teams");
            return true;
        }

        new TeamChooseGUI().open(player);
        return false;
    }
}
