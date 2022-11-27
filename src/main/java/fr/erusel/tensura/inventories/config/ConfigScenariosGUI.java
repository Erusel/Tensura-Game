package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.Scenarios;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfigScenariosGUI extends FastInv {

    public ConfigScenariosGUI() {
        super(45, "Tensura Configuration | Scenarios");

        for (Scenarios scenarios : Scenarios.values()){
            ItemBuilder itemBuilder;
            if (Main.getInstance().getGameManager().getActivatedScenarios().contains(scenarios)) itemBuilder = new ItemBuilder(Material.GREEN_WOOL);
            else itemBuilder = new ItemBuilder(Material.RED_WOOL);
            itemBuilder.name("§7" + scenarios.getName());
            addItem(itemBuilder.build(), event -> clickScenario(event, scenarios));
        }}

    public void clickScenario(InventoryClickEvent event, Scenarios scenarios){
        Player player = (Player) event.getWhoClicked();
        if (Main.getInstance().getGameManager().getActivatedScenarios().contains(scenarios)) {
            Main.getInstance().getGameManager().getActivatedScenarios().remove(scenarios);
            event.getCurrentItem().setType(Material.RED_WOOL);
            player.updateInventory();
        }
        else {
            Main.getInstance().getGameManager().getActivatedScenarios().add(scenarios);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            player.updateInventory();

        }
    }



}
