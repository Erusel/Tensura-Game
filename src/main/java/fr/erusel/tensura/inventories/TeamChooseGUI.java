package fr.erusel.tensura.inventories;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.managers.GameManager;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class TeamChooseGUI extends FastInv {


    public TeamChooseGUI() {
        super(27, "Team Chooser");

        ItemBuilder redTeam = new ItemBuilder(Material.RED_WOOL)
                .name("§cRed Team")
                .addLore("§7------------");
        ItemBuilder blueTeam = new ItemBuilder(Material.BLUE_WOOL)
                .name("§9Blue Team")
                .addLore("§7------------");
        ItemBuilder greenTeam = new ItemBuilder(Material.GREEN_WOOL)
                .name("§2Green Team")
                .addLore("§7------------");
        ItemBuilder yellowTeam = new ItemBuilder(Material.YELLOW_WOOL)
                .name("§eYellow Team")
                .addLore("§7------------");

        for (UUID uuid : GameManager.getInstance().getTeamManager().getRedTeamPlayers()) redTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());
        for (UUID uuid : GameManager.getInstance().getTeamManager().getBlueTeamPlayers()) blueTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());
        for (UUID uuid : GameManager.getInstance().getTeamManager().getGreenTeamPlayers()) greenTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());
        for (UUID uuid : GameManager.getInstance().getTeamManager().getYellowTeamPlayers()) yellowTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());

        setItem(10, redTeam.build(), this::redTeam);
        setItem(12, blueTeam.build(), this::blueTeam);
        setItem(14, greenTeam.build(), this::greenTeam);
        setItem(16, yellowTeam.build(), this::yellowTeam);

    }

    private void redTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        GameManager.getInstance().getTeamManager().addRedTeamPlayer(player);
        player.sendMessage("You joined red team !");
        player.closeInventory();
    }

    private void blueTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        GameManager.getInstance().getTeamManager().addBlueTeamPlayer(player);
        player.sendMessage("You joined blue team !");
        player.closeInventory();
    }

    private void greenTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        GameManager.getInstance().getTeamManager().addGreenTeamPlayer(player);
        player.sendMessage("You joined green team !");
        player.closeInventory();
    }

    private void yellowTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        GameManager.getInstance().getTeamManager().addYellowTeamPlayer(player);
        player.sendMessage("You joined yellow team !");
        player.closeInventory();
    }

}
