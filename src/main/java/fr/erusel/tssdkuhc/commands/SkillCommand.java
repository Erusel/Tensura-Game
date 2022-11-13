package fr.erusel.tssdkuhc.commands;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.GState;
import fr.erusel.tssdkuhc.inventorys.SkillGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (!Main.getInstance().getGameManager().getGameState().equals(GState.PLAYING)) return true;
        if (Main.getInstance().getGameManager().getDeadPlayers().contains(player.getUniqueId())){
            player.sendMessage("Vous ne pouvez faire cela, vous êtes mort lol nullos");
            return true;
        }
        new SkillGUI(player).open(player);

        return true;
    }
}
