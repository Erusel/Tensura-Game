package fr.erusel.tssdkuhc.commands;

import fr.erusel.tssdkuhc.enums.Skills;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TensuraTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1){
            list.add("start");
            list.add("sethost");
            list.add("giveskill");
            list.add("harvestfestival");
        }
        if (args.length == 2){
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
                for (Skills skills : Skills.values()) list.add(skills.getSkillName());
            }



        }









        return list;
    }
}
