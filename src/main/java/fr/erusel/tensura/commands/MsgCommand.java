package fr.erusel.tensura.commands;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.erusel.tensura.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MsgCommand implements CommandExecutor {

    GameManager gameManager;
    PlayerManager playerManager;

    public MsgCommand(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)){
            return false;
        }

        if (!gameManager.getGameState().equals(GState.PLAYING)){
            return true;
        }
        if (!playerManager.getGPlayerByUUID(player.getUniqueId()).haveSkill(Skills.TELEKINESIS)){
            return true;
        }
        if (args.length == 0){
            player.sendMessage("§cPlease enter a Player");
            return true;
        }
        if (Bukkit.getPlayer(args[0]) == null){
            player.sendMessage("§cPlayer not found");
            return true;
        }

        StringBuilder message = new StringBuilder();
        List<String> t = new ArrayList<>();
        for (String text : args) {
            t.add(text + " ");
        }
        t.remove(0);
        for (String i : t) {
            message.append(i);
        }

        player.sendMessage("§6" + player.getName() + "§8>§7 " + message);
        Bukkit.getPlayer(args[0]).sendMessage("§6" + player.getName() + "§8>§7 " + message);

        return true;
    }
}
