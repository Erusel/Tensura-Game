package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.enums.GState;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigMainGUI extends FastInv {

    public ConfigMainGUI() {
        super(27, "Tensura Config");

        // Mode Item
        ItemBuilder mode;
        if (getGameManager().getGameState().equals(GState.PLAYING)) mode = new ItemBuilder(Material.BARRIER);
        else mode = new ItemBuilder(Material.BOOK);
        mode.name("§7Mode")
                .addLore("§7Change or set the Gamemode");

        // Scenario Item
        ItemBuilder scenario;
        if (getGameManager().getGameState().equals(GState.PLAYING)) scenario = new ItemBuilder(Material.BARRIER);
        else scenario = new ItemBuilder(Material.GOLD_BLOCK);
        scenario.name("§7Scenarios")
                .addLore("§7Add and remove scenarios");

        // Skills Item
        ItemBuilder skills;
        if (getGameManager().getGameState().equals(GState.PLAYING)) skills = new ItemBuilder(Material.BARRIER);
        else skills = new ItemBuilder(Material.DIAMOND_BLOCK);
        skills.name("§7Skills")
                .addLore("§7A voir, sa m'etonne que sa reste");

        // Settings Item
        ItemBuilder settings;
        settings = new ItemBuilder(Material.REDSTONE_BLOCK);
        settings.name("§7Settings")
                .addLore("§7Change game settings");

        setItem(10, mode.build(), this::mode);
        setItem(12, scenario.build(), this::scenarios);
        setItem(14, skills.build(), this::skills);
        setItem(16, settings.build(), this::settings);
    }

    public void mode(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) return;
        new ConfigModeGUI().open(player);
    }

    public void scenarios(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) return;
        new ConfigScenariosGUI().open(player);
    }

    public void skills(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) return;
        player.closeInventory();
    }

    public void settings(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) return;
        new ConfigSettingsGUI().open(player);
    }


}
