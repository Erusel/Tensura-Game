package fr.erusel.tensura.items.charybdis;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CharybdisPartTwoItem {

    public static ItemStack getItemstack(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.BONE);
        itemBuilder.name("§6Charybdis Part");
        itemBuilder.addLore("§5Part 2");
        return itemBuilder.build();
    }

    public static void onUse(PlayerInteractEvent event){
        event.getPlayer().sendMessage("Charybdis part 1 mageule");
    }



}