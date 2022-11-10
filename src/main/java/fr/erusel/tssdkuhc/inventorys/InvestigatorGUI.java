package fr.erusel.tssdkuhc.inventorys;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class InvestigatorGUI extends FastInv {

    public InvestigatorGUI(Skill skill) {
        super(45, "Investigator");

        for (UUID uuid : Main.getInstance().getGameManager().getPlayerList()){
            if (Bukkit.getPlayer(uuid) == null) continue;
            Player p = Bukkit.getPlayer(uuid);

            addItem(new ItemBuilder(Material.PLAYER_HEAD).name("§7" + p.getName()).skullmeta(p.getName()).build(), e -> openInventory(p, e, skill));

        }

    }


    public void openInventory(Player victim, InventoryClickEvent event, Skill skill){
        Player player = (Player) event.getWhoClicked();
        skill.activateCooldown();
        
        FastInv inv = new FastInv(54, victim.getName() + " inventory");
        for (int i=9; i<18; i++) {
            inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(" ").build());
        }
        int j=18;
        int k=0;
        for (ItemStack itemStack : victim.getInventory().getContents()){
            if (j==59) {
                break;
            }

            if (j>53) {
                inv.setItem(k,itemStack);
                k++;
            }

            else {
                inv.setItem(j, itemStack);
            }
            j++;

        }
        inv.open(player);
    }

}
