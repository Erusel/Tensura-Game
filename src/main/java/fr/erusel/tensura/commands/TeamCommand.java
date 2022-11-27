package fr.erusel.tensura.commands;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.inventories.TeamChooseGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (Main.getInstance().getGameManager().getGameState().equals(GState.WAITING)){
            if (Main.getInstance().getGameManager().getGameMode().haveTeam()){
                new TeamChooseGUI().open(player);
            }else {
                player.sendMessage("This gamemode have no teams");
            }
        }

        return false;
    }
}
