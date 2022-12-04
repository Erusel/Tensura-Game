package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.enums.Modes;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;

public class ConfigModeGUI extends FastInv {

    public ConfigModeGUI() {
        super(9, "Tensura Config | Mode");

        ItemBuilder battleRoyal = new ItemBuilder(Material.DIAMOND_SWORD)
                .name("§7Battle Royal")
                .flags(ItemFlag.HIDE_ATTRIBUTES)
                .addLore("§7The last survivant won the game !")
                .addLore("§7------------------")
                .addLore("§7Team :§1 No");

        ItemBuilder charybdis = new ItemBuilder(Material.ENDERMAN_SPAWN_EGG)
                .name("§7Charybdis Hunt")
                .flags(ItemFlag.HIDE_ATTRIBUTES)
                .addLore("§7Find all charybdis part with your team !")
                .addLore("§7------------------")
                .addLore("§7Team :§1 Yes");



        setItem(1, battleRoyal.build(), this::battleRoyal);
        setItem(2, charybdis.build(), this::charybdis);
    }

    public void battleRoyal(InventoryClickEvent event){
        getGameManager().setGameMode(Modes.BATTLE_ROYAL);
        new ConfigMainGUI().open((Player) event.getWhoClicked());
    }

    public void charybdis(InventoryClickEvent event){
        getGameManager().setGameMode(Modes.CHARYBDIS);
        new ConfigMainGUI().open((Player) event.getWhoClicked());
    }


}
