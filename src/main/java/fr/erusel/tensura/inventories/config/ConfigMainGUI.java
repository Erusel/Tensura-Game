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
        ItemBuilder mode = getGameManager().getGameState().equals(GState.PLAYING)
                ? new ItemBuilder(Material.GREEN_WOOL)
                : new ItemBuilder(Material.BARRIER)
                .name("§7Mode")
                .addLore("§7Change or set the Gamemode");

        // Scenario Item
        ItemBuilder scenario = getGameManager().getGameState().equals(GState.PLAYING)
                ? new ItemBuilder(Material.GREEN_WOOL)
                : new ItemBuilder(Material.BARRIER)
                .name("§7Scenarios")
                .addLore("§7Change or set the Scenarios");

        // Skills Item
        ItemBuilder skills = getGameManager().getGameState().equals(GState.PLAYING)
                ? new ItemBuilder(Material.GREEN_WOOL)
                : new ItemBuilder(Material.BARRIER)
                .name("§7Skills")
                .addLore("§7Change or set the Skills");

        // Settings Item
        ItemBuilder settings = new ItemBuilder(Material.REDSTONE_BLOCK);
        settings.name("§7Settings")
                .addLore("§7Change game settings");

        setItem(10, mode.build(), this::mode);
        setItem(12, scenario.build(), this::scenarios);
        setItem(14, skills.build(), this::skills);
        setItem(16, settings.build(), this::settings);
    }

    public void mode(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) {
            return;
        }
        new ConfigModeGUI().open(player);
    }

    public void scenarios(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) {
            return;
        }
        new ConfigScenariosGUI().open(player);
    }

    public void skills(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) {
            return;
        }
        player.closeInventory();
    }

    public void settings(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!getGameManager().getGameState().equals(GState.WAITING)) {
            return;
        }
        new ConfigSettingsGUI().open(player);
    }


}
