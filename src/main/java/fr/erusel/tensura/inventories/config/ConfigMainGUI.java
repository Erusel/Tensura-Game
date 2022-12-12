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
        ItemBuilder mode = getGameManager().getGameState().equals(GState.WAITING)
                ? new ItemBuilder(Material.GREEN_WOOL)
                : new ItemBuilder(Material.BARRIER);
        mode.name("§aMode")
                .lore("§7Change the mode");


        // Scenario Item
        ItemBuilder scenario = getGameManager().getGameState().equals(GState.WAITING)
                ? new ItemBuilder(Material.ORANGE_WOOL)
                : new ItemBuilder(Material.BARRIER);
        scenario.name("§7Scenario")
                .addLore("§7Change the Scenario");

        // Skills Item
        ItemBuilder skills = getGameManager().getGameState().equals(GState.WAITING)
                ? new ItemBuilder(Material.YELLOW_WOOL)
                : new ItemBuilder(Material.BARRIER);
        skills.name("§7Skills")
                .addLore("§7Change the Skills");

        // Settings Item
        ItemBuilder settings = getGameManager().getGameState().equals(GState.WAITING)
                ? new ItemBuilder(Material.REDSTONE_BLOCK)
                : new ItemBuilder(Material.BARRIER);
        settings.name("§7Settings")
                        .lore("§7Change the Settings");

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
