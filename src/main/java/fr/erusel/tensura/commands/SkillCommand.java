package fr.erusel.tensura.commands;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.inventories.SkillGUI;
import fr.erusel.tensura.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (!GameManager.getInstance().getGameState().equals(GState.PLAYING)) return true;
        if (GameManager.getInstance().getDeadPlayers().contains(player.getUniqueId())){
            player.sendMessage("§cYou can't do this, you are dead.");
            return true;
        }
        new SkillGUI(player).open(player);

        return true;
    }
}
