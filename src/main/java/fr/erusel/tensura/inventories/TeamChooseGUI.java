package fr.erusel.tensura.inventories;

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

        for (UUID uuid : getGameManager().getTeamManager().getRedTeamPlayers()) redTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());
        for (UUID uuid : getGameManager().getTeamManager().getBlueTeamPlayers()) blueTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());
        for (UUID uuid : getGameManager().getTeamManager().getGreenTeamPlayers()) greenTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());
        for (UUID uuid : getGameManager().getTeamManager().getYellowTeamPlayers()) yellowTeam.addLore("§6" + Bukkit.getPlayer(uuid).getName());

        setItem(10, redTeam.build(), this::redTeam);
        setItem(12, blueTeam.build(), this::blueTeam);
        setItem(14, greenTeam.build(), this::greenTeam);
        setItem(16, yellowTeam.build(), this::yellowTeam);

    }

    private void redTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getGameManager().getTeamManager().addRedTeamPlayer(player);
        player.sendMessage("§6You joined red team !");
        player.updateInventory();
    }

    private void blueTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getGameManager().getTeamManager().addBlueTeamPlayer(player);
        player.sendMessage("§6You joined blue team !");
        player.updateInventory();
    }

    private void greenTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getGameManager().getTeamManager().addGreenTeamPlayer(player);
        player.sendMessage("§6You joined green team !");
        player.updateInventory();
    }

    private void yellowTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getGameManager().getTeamManager().addYellowTeamPlayer(player);
        player.sendMessage("§6You joined yellow team !");
        player.updateInventory();
    }

}
