package fr.erusel.tssdkuhc.inventorys;

import fr.erusel.tssdkuhc.Main;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InvestigatorGUI extends FastInv {

    public InvestigatorGUI() {
        super(45, "Investigator");

        for (UUID uuid : Main.getInstance().getGameManager().getPlayerList()){
            if (Bukkit.getPlayer(uuid) == null) continue;
            Player p = Bukkit.getPlayer(uuid);

            addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + p.getName()).skullmeta(p.getName()).build(), e -> openInventory(p, e));

        }

    }


    public void openInventory(Player victim, InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        FastInv inv = new FastInv(45, victim.getName() + " inventory");
        for (ItemStack itemStack : victim.getInventory().getContents()){
            addItem(itemStack);
        }
        inv.open(player);
    }

}
