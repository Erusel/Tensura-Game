package fr.erusel.tensura.inventories.config;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigMainGUI extends FastInv {

    public ConfigMainGUI() {
        super(27, "Tensura Configuration");

        setItem(11, new ItemBuilder(Material.NETHER_STAR).name("§7Mode").build(), this::mode);
        setItem(12, new ItemBuilder(Material.NETHER_STAR).name("§7Scenarios").build(), this::scenarios);
        setItem(13, new ItemBuilder(Material.NETHER_STAR).name("§7Skills").build(), this::skills);
        setItem(14, new ItemBuilder(Material.NETHER_STAR).name("§7Others").build(), this::others);
    }

    public void mode(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        new ConfigModeGUI().open(player);
    }

    public void scenarios(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("scenarios");
        player.closeInventory();
    }

    public void skills(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("skills");
        player.closeInventory();
    }

    public void others(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("others");
        player.closeInventory();
    }


}
