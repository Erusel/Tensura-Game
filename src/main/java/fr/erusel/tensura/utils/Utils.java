package fr.erusel.tensura.utils;

import fr.erusel.tensura.enums.Prefixes;
import fr.erusel.tensura.managers.GameManager;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

    public static ItemStack[] items = {
            new ItemBuilder(Material.DIAMOND).build(),
            new ItemBuilder(Material.IRON_INGOT).build(),
            new ItemBuilder(Material.GOLD_INGOT).build(),
            new ItemBuilder(Material.GOLDEN_APPLE).build(),
            new ItemBuilder(Material.ENDER_PEARL).build(),
            new ItemBuilder(Material.BOW).build(),
            new ItemBuilder(Material.ARROW).build(),
            new ItemBuilder(Material.IRON_HELMET).build(),
            new ItemBuilder(Material.IRON_CHESTPLATE).build(),
            new ItemBuilder(Material.IRON_LEGGINGS).build(),
            new ItemBuilder(Material.IRON_BOOTS).build(),
            new ItemBuilder(Material.FURNACE_MINECART).name("§elol useless").build(),
            new ItemBuilder(Material.WATER_BUCKET).build(),
            new ItemBuilder(Material.LAVA_BUCKET).build(),
            new ItemBuilder(Material.CAKE).name("§eThe lie").build(),
    };

    public static void resetPlayer(Player player, GameManager gameManager){
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        player.setFoodLevel(20);

        // Set attribute
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue());

        // reset Inventory
        player.getInventory().clear();
        if (player.getInventory().getBoots() != null) {
            player.getInventory().getBoots().setType(Material.AIR);
        }
        if (player.getInventory().getLeggings() != null) {
            player.getInventory().getLeggings().setType(Material.AIR);
        }
        if (player.getInventory().getChestplate() != null) {
            player.getInventory().getChestplate().setType(Material.AIR);
        }
        if (player.getInventory().getHelmet() != null) {
            player.getInventory().getHelmet().setType(Material.AIR);
        }
    }

    public static void VoiceOfTheWorldBroadcast(String message){
        Bukkit.broadcastMessage(Prefixes.VOICE_OF_THE_WORLD.getText() + message);
    }

    public static String getTime(Integer secs) {
        return String.format("§7%02d§7:§7%02d§7:§7%02d", ((secs / 3600) % 24), (secs % 3600) / 60, secs % 60);
    }
}
