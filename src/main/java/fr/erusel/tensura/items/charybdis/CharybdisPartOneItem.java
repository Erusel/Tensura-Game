package fr.erusel.tensura.items.charybdis;

import fr.erusel.tensura.objects.GItem;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CharybdisPartOneItem extends GItem {

    @Override
    public void onUse(PlayerInteractEvent event) {
        event.getPlayer().sendMessage("Charybdis part 1 mageule");
    }

    @Override
    public ItemStack getItemstack(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.BONE);
        itemBuilder.name("§6Charybdis Part");
        itemBuilder.addLore("§5Part 1");
        return itemBuilder.build();
    }




}
