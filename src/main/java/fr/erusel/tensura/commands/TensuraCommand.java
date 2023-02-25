package fr.erusel.tensura.commands;

import fr.erusel.tensura.enums.GItems;
import fr.erusel.tensura.enums.Prefixes;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.inventories.config.ConfigMainGUI;
import fr.erusel.tensura.objects.GameElement;
import fr.erusel.tensura.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TensuraCommand extends GameElement implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }

        // No Host Command
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
        if (args[0].equalsIgnoreCase("sethost")) {
            if (!player.isOp()){
                player.sendMessage("§cYou don't have the permission to do this !");
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null){
                player.sendMessage("§cPlayer not found");
                return true;
            }
            getGameManager().setPlayerHost(Bukkit.getPlayer(args[1]));
            player.sendMessage("Player " + Bukkit.getPlayer(args[1]).getName() + " has been set as Host");
            return true;
        }

        if (args[0].equalsIgnoreCase("start")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            getGameManager().startGame(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("config")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            new ConfigMainGUI().open(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("pregen")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            getWorldManager().deletePlayingWorld();
            Utils.VoiceOfTheWorldBroadcast("Creating world...");
            getWorldManager().createPlayingWorld();
            Utils.VoiceOfTheWorldBroadcast("Successful");
            Utils.VoiceOfTheWorldBroadcast("Creating personal dimension...");
            getWorldManager().createPersonalDimension();
            Utils.VoiceOfTheWorldBroadcast("Successful");
            return true;
        }

        if (args[0].equalsIgnoreCase("giveskill")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null){
                player.sendMessage("§cPlayer not found");
                return true;
            }
            getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).addSkill(Skills.valueOf(args[2]).createInstance());
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD.getText() + "Successfully given " + Skills.valueOf(args[2]).getSkillName() + " to " + Bukkit.getPlayer(args[1]).getName());
            return true;
        }
        if (args[0].equalsIgnoreCase("setrace")) {
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null){
                player.sendMessage("§cPlayer not found");
                return true;
            }
            if (getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).getRace().getName().equalsIgnoreCase(args[2])) {
                player.sendMessage("§c" + args[1] + " est déjà " + args[2]);
                return true;
            }
            getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).setRace(Races.valueOf(args[2]).createInstance());
            player.sendMessage(Prefixes.VOICE_OF_THE_WORLD.getText() + "Successfully given " + Races.valueOf(args[2]).getName() + " to " + Bukkit.getPlayer(args[1]).getName());
            getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).getRace().onGive(Bukkit.getPlayer(args[1]));
            Utils.resetPlayer(Bukkit.getPlayer(args[1]), getGameManager());
            return true;
        }
        if (args[0].equalsIgnoreCase("resetcooldown")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null){
                player.sendMessage("§cPlayer not found");
                return true;
            }
            getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId()).getPlayerSkills()
                    .forEach(skill -> skill.setCurrentCooldown(0));
            player.sendMessage("§3Cooldown reseted for " + Bukkit.getPlayer(args[1]).getName());
            return true;
        }
        if (args[0].equalsIgnoreCase("harvestfestival")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null){
                player.sendMessage("§cPlayer not found");
                return true;
            }
            getPlayerManager().getGPlayerByUUID(Bukkit.getPlayer(args[1]).getUniqueId())
                    .launchHarvestFestival();
            return true;
        }
        if (args[0].equalsIgnoreCase("broadcast")){
            if (!getGameManager().getHostUUID().equals(player.getUniqueId())){
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
            Utils.VoiceOfTheWorldBroadcast(message.toString());
        }
        if (args[0].equalsIgnoreCase("giveitem")){
            if (args[1] == null){
                return false;
            }
            for (GItems gItems : GItems.values()){
                if (gItems.name().equalsIgnoreCase(args[1])){
                    player.getInventory().addItem(gItems.createInstance().getItemstack());
                    player.sendMessage("§7Item sucessfully given !");
                    return true;
                }
            }
            player.sendMessage("§cItem not found !");

        }
        return true;
    }
}
