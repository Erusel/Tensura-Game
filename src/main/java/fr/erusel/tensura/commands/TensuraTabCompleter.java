package fr.erusel.tensura.commands;

import fr.erusel.tensura.enums.GState;
import fr.erusel.tensura.enums.Races;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TensuraTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        Player p = (Player) sender;

        List<String> list = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        int arg = 0;

        if (args.length == 1){
            if (GameManager.getInstance().getGameState().equals(GState.WAITING)) {
                list.add("sethost");
                if (GameManager.getInstance().playerIsHost(p)){
                    list.add("config");
                    list.add("start");
                    list.add("pregen");
                    list.add("broadcast");
                    list.add("help");
                }
            }
            if (GameManager.getInstance().getGameState().equals(GState.PLAYING)) {
                if (GameManager.getInstance().playerIsHost(p)){
                    list.add("giveskill");
                    list.add("setrace");
                    list.add("harvestfestival");
                    list.add("resetcooldown");
                    list.add("broadcast");
                }
            }
        }
        if (args.length == 2){
            arg = 1;
            if (args[0].equalsIgnoreCase("sethost")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("giveskill")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("setrace")) {
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("resetcooldown")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("harvestfestival")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("help")) {
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }

        }
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("giveskill")){
                arg = 2;
                for (Skills skills : Skills.values()) list.add(skills.name());
            }
            if (args[0].equalsIgnoreCase("setrace")) {
                arg = 2;
                for (Races races : Races.values()) list.add(races.name());
            }
        }

        StringUtil.copyPartialMatches(args[arg], list, completions);
        Collections.sort(completions);

        return completions;
    }
}
