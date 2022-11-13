package fr.erusel.tssdkuhc.commands;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.Skills;
import fr.erusel.tssdkuhc.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TensuraCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length == 0){
            player.sendMessage("-----------------------§bTensura§f-----------------------");
            player.sendMessage("\n\n§a/tensura sethost [Player] §7- §eSet the player host of the game");
            player.sendMessage("\n§a/tensura start §7- §eStart the game if a player is host");
            return true;
        }

        if (args[0].equalsIgnoreCase("start")){
            Main.getInstance().getGameManager().startGame();
        }
        if (args[0].equalsIgnoreCase("sethost")) {
            if (Bukkit.getPlayer(args[1]) != null){
                Main.getInstance().getGameManager().setPlayerHost(Bukkit.getPlayer(args[1]));
                player.sendMessage("Player " + Bukkit.getPlayer(args[1]).getName() + " has been set as Host");
                return true;
            }else player.sendMessage("§cPlayer not found");
        }
        if (args[0].equalsIgnoreCase("giveskill")){
            if (Bukkit.getPlayer(args[1]) != null){
                Skills.valueOf(args[2]);
                try {
                    Main.getInstance().getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).addSkill((Skill) Skills.valueOf(args[2]).getSkillClass().getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Main.VOICE_OF_THE_WORLD_PREFIX + "Successfully gived " + Skills.valueOf(args[2]).getSkillName() + " to " + Bukkit.getPlayer(args[1]).getName());
            }else player.sendMessage("§cPlayer not found");
        }
        if (args[0].equalsIgnoreCase("harvestfestival")){
            if (Bukkit.getPlayer(args[1]) != null){
                Main.getInstance().getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).launchHarvestFestival();
            }
        }

        return false;
    }
}
