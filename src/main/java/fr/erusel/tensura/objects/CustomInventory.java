package fr.erusel.tensura.objects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public interface CustomInventory {

    String getName();

    Supplier<ItemStack[]> getContents(Player player);

    void onClick(Player player, Inventory inventory, InventoryView inventoryView, ItemStack current, int slot, boolean isLeftClick, boolean isRightClick) throws InstantiationException, IllegalAccessException;

    int getRows();

    default int getSlots() {
        return getRows() * 9;
    }
}