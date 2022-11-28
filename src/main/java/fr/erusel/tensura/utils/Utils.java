package fr.erusel.tensura.utils;

import fr.erusel.tensura.enums.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Utils {

    public static void resetPlayer(Player player){
        player.setHealth(20);
        player.setFoodLevel(20);

        // Set attribute
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.15f);


        // reset Inventory
        player.getInventory().clear();
        if (player.getInventory().getBoots() != null) player.getInventory().getBoots().setType(Material.AIR);
        if (player.getInventory().getLeggings() != null) player.getInventory().getLeggings().setType(Material.AIR);
        if (player.getInventory().getChestplate() != null) player.getInventory().getChestplate().setType(Material.AIR);
        if (player.getInventory().getHelmet() != null) player.getInventory().getHelmet().setType(Material.AIR);
    }

    public static void VoiceOfTheWorldBroadcast(String message){
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + message);
    }

}
