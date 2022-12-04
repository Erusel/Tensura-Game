package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.enums.Scenarios;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigScenariosGUI extends FastInv {

    public ConfigScenariosGUI() {
        super(45, "Tensura Config | Scenarios");

        for (Scenarios scenarios : Scenarios.values()){
            ItemBuilder itemBuilder;
            if (getGameManager().getActivatedScenarios().contains(scenarios)) itemBuilder = new ItemBuilder(Material.GREEN_WOOL);
            else itemBuilder = new ItemBuilder(Material.RED_WOOL);
            itemBuilder.name("§6" + scenarios.getName());
            itemBuilder.addLore("§7" + scenarios.getDescription());
            addItem(itemBuilder.build(), event -> clickScenario(event, scenarios));
        }}

    public void clickScenario(InventoryClickEvent event, Scenarios scenarios){
        Player player = (Player) event.getWhoClicked();
        if (getGameManager().getActivatedScenarios().contains(scenarios)) {
            getGameManager().getActivatedScenarios().remove(scenarios);
            event.getCurrentItem().setType(Material.RED_WOOL);
            player.updateInventory();
        }
        else {
            getGameManager().getActivatedScenarios().add(scenarios);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            player.updateInventory();

        }
    }



}
