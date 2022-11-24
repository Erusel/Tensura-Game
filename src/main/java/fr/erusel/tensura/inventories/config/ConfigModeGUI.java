package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Modes;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigModeGUI extends FastInv {

    public ConfigModeGUI() {
        super(9, "Tensura Configuration | Mode");
        addItem(new ItemBuilder(Material.DIAMOND_SWORD).name("§7Battle Royal").build(), this::battleRoyal);
        addItem(new ItemBuilder(Material.ENDERMAN_SPAWN_EGG).name("§7Charybdis Hunt").build(), this::charybdis);
    }

    public void battleRoyal(InventoryClickEvent event){
        Main.getInstance().getGameManager().setGameMode(Modes.BATTLE_ROYAL);
        new ConfigMainGUI().open((Player) event.getWhoClicked());
    }

    public void charybdis(InventoryClickEvent event){
        Main.getInstance().getGameManager().setGameMode(Modes.CHARYBDIS);
        new ConfigMainGUI().open((Player) event.getWhoClicked());
    }


}
