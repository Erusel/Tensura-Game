package fr.erusel.tensura.objects;

import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface GItem {
    default void onUse(PlayerInteractEvent event){

    }

    default void onUseAtEntity(PlayerInteractAtEntityEvent event){

    }
    ItemStack getItemstack();
}
