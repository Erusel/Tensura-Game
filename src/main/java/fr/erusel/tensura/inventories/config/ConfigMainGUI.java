package fr.erusel.tensura.inventories.config;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigMainGUI extends FastInv {

    public ConfigMainGUI() {
        super(27, "Tensura Config");

        setItem(10, new ItemBuilder(Material.BOOK).name("§7Mode").addLore("§7Change or set the Gamemode").build(), this::mode);
        setItem(12, new ItemBuilder(Material.GOLD_BLOCK).name("§7Scenarios").addLore("§7Add and remove scenarios").build(), this::scenarios);
        setItem(14, new ItemBuilder(Material.DIAMOND_BLOCK).name("§7Skills").addLore("§7A voir, sa m'etonne que sa reste").build(), this::skills);
        setItem(16, new ItemBuilder(Material.REDSTONE_BLOCK).name("§7Settings").addLore("§7Main Settings").build(), this::settings);
    }

    public void mode(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        new ConfigModeGUI().open(player);
    }

    public void scenarios(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        new ConfigScenariosGUI().open(player);
    }

    public void skills(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("skills");
        player.closeInventory();
    }

    public void settings(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        new ConfigSettingsGUI().open(player);
    }


}
