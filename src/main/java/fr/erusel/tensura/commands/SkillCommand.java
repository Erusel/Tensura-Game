package fr.erusel.tensura.commands;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.inventories.SkillGUI;
import fr.erusel.tensura.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCommand implements CommandExecutor {

    GameManager gameManager;

    public SkillCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return false;

        if (!gameManager.getGameState().equals(GState.PLAYING)) return true;
        if (gameManager.getDeadPlayers().contains(player.getUniqueId())){
            player.sendMessage("§cYou can't do this, you are dead.");
            return true;
        }
        new SkillGUI(player).open(player);

        return true;
    }
}
