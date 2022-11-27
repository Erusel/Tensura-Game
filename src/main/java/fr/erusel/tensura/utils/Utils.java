package fr.erusel.tensura.utils;

import fr.erusel.tensura.enums.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Utils {

    public static void resetPlayer(Player player){
        player.setHealth(20);
        player.setFoodLevel(20);

        // Set attribute
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
        player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).getDefaultValue());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getDefaultValue());


        // reset Inventory
        player.getInventory().clear();
        player.getInventory().getBoots().setType(null);
        player.getInventory().getLeggings().setType(null);
        player.getInventory().getChestplate().setType(null);
        player.getInventory().getHelmet().setType(null);
    }

    public static void VoiceOfTheWorldBroadcast(String message){
        Bukkit.broadcastMessage(Prefixs.VOICE_OF_THE_WORLD.getText() + message);
    }

}
