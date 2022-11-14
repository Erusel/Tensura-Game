package fr.erusel.tssdkuhc.commands;

import fr.erusel.tssdkuhc.enums.Items;
import fr.erusel.tssdkuhc.enums.Skills;
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

        List<String> list = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        int arg = 0;

        if (args.length == 1){
            list.add("start");
            list.add("sethost");
            list.add("giveskill");
            list.add("harvestfestival");
        }
        if (args.length == 2){
            arg = 1;
            if (args[0].equalsIgnoreCase("sethost")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("giveskill")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }
            if (args[0].equalsIgnoreCase("harvestfestival")){
                for (Player player : Bukkit.getOnlinePlayers()) list.add(player.getName());
            }

        }
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("giveskill")){
                arg = 2;
                for (Skills skills : Skills.values()) list.add(skills.name());
            }
            if (args[0].equalsIgnoreCase("giveitem")){
                arg = 2;
                for (Items items : Items.values()) list.add(items.name());
            }
        }


        StringUtil.copyPartialMatches(args[arg], list, completions);
        Collections.sort(completions);

        return completions;
    }
}
