package fr.erusel.tensura.commands;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Prefixs;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.config.ConfigMainGUI;
import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TensuraCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length == 0  || args[0].equalsIgnoreCase("help")){
            player.sendMessage("-----------------------§bTensura§f-----------------------");
            player.sendMessage("\n\n§a/tensura sethost [Player] §7- §eSet the player host of the game");
            player.sendMessage("\n§a/tensura start §7- §eStart the game if a player is host");
            player.sendMessage("\n§a/tensura config §7- §eAllow you to config the settings of the game");
            player.sendMessage("\n§a/tensura giveskill [Player] [Skill] §7- §eGive to the player a skill (only work during a game)");
            player.sendMessage("\n§a/tensura setrace [Player] [race] §7- §eChange the race of the player (only work during a game)");
            player.sendMessage("\n§a/tensura resetcooldown [Player] §7- §eReset the cooldown of every skill of the player");
            player.sendMessage("\n§a/tensura broadcast [Message] §7- §eSend a broadcast message");
            return true;
        }

        if (args[0].equalsIgnoreCase("start")){
            Main.getInstance().getGameManager().startGame(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("config")){
            new ConfigMainGUI().open(player);
            return true;
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
                    Main.getInstance().getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).addSkill(Skills.valueOf(args[2]).getSkillClass().getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Successfully given " + Skills.valueOf(args[2]).getSkillName() + " to " + Bukkit.getPlayer(args[1]).getName());
            }else player.sendMessage("§cPlayer not found");
        }
        if (args[0].equalsIgnoreCase("setrace")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                Races.valueOf(args[2]);
                try {
                    Main.getInstance().getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).setRace(Races.valueOf(args[2]).getRaceClass().getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + "Successfully given " + Races.valueOf(args[2]).getName() + " to " + Bukkit.getPlayer(args[1]).getName());
            }
            else player.sendMessage("§cPlayer not found");
        }
        if (args[0].equalsIgnoreCase("resetcooldown")){
            if (Bukkit.getPlayer(args[1]) != null){
                for (Skill skill : Main.getInstance().getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).getPlayerSkills()){
                    skill.setCurrentCooldown(0);
                }
                player.sendMessage("§3Cooldown reseted for " + Bukkit.getPlayer(args[1]).getName());
            }else player.sendMessage("§cPlayer not found");
        }
        if (args[0].equalsIgnoreCase("harvestfestival")){
            if (Bukkit.getPlayer(args[1]) != null){
                Main.getInstance().getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).launchHarvestFestival();
            }
        }
        if (args[0].equalsIgnoreCase("broadcast")){
            StringBuilder message = new StringBuilder();
            List<String> t = new ArrayList<>();
            for (String text : args){
                t.add(text + " ");
            }
            t.remove(0);
            for (String i : t){
                message.append(i);
            }
            Utils.VoiceOfTheWorldBroadcast(message.toString());

        }
        if (args[0].equalsIgnoreCase("pregen")){

        }
        return true;
    }
}
