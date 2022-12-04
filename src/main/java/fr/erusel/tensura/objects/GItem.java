package fr.erusel.tensura.objects;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class GItem {
    public abstract void onUse(PlayerInteractEvent event);
    public abstract ItemStack getItemstack();
}
