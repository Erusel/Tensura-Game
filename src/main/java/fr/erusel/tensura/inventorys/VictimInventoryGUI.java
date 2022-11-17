package fr.erusel.tensura.inventorys;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VictimInventoryGUI extends FastInv {

    public VictimInventoryGUI(Player victim) {
        super(54, victim.getName() + "'s Inventory");

        for (int i=9; i<18; i++) {
            setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(" ").build());
        }
        int j=18;
        int k=0;
        for (ItemStack itemStack : victim.getInventory().getContents()){
            if (j==59) break;
            if (j>53) {
                setItem(k,itemStack);
                k++;
            } else {
                setItem(j, itemStack);
            }
            j++;
        }
    }
}
