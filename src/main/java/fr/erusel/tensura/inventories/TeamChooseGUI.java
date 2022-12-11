package fr.erusel.tensura.inventories;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

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

        getTeamManager().getRedTeamPlayers().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            redTeam.addLore("§c" + player.getName());
        });

        getTeamManager().getBlueTeamPlayers().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            blueTeam.addLore("§c" + player.getName());
        });

        getTeamManager().getGreenTeamPlayers().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            greenTeam.addLore("§c" + player.getName());
        });

        getTeamManager().getYellowTeamPlayers().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            yellowTeam.addLore("§c" + player.getName());
        });

        setItem(10, redTeam.build(), this::redTeam);
        setItem(12, blueTeam.build(), this::blueTeam);
        setItem(14, greenTeam.build(), this::greenTeam);
        setItem(16, yellowTeam.build(), this::yellowTeam);

    }

    private void redTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getTeamManager().addRedTeamPlayer(player);
        player.sendMessage("§6You joined red team !");
        player.updateInventory();
    }

    private void blueTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getTeamManager().addBlueTeamPlayer(player);
        player.sendMessage("§6You joined blue team !");
        player.updateInventory();
    }

    private void greenTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getTeamManager().addGreenTeamPlayer(player);
        player.sendMessage("§6You joined green team !");
        player.updateInventory();
    }

    private void yellowTeam(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        getTeamManager().addYellowTeamPlayer(player);
        player.sendMessage("§6You joined yellow team !");
        player.updateInventory();
    }

}
