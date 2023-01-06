package fr.erusel.tensura.managers;

import fr.erusel.tensura.enums.GItems;
import fr.erusel.tensura.objects.GItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GItemManager {

    private static GItemManager instance;

    private final List<GItem> registeredGItems = new ArrayList<>();

    public GItemManager() {
        instance = this;
        registerItems();
    }

    public void registerItems(){
        for (GItems gItems : GItems.values()){
            registeredGItems.add(gItems.createInstance());
        }
    }
    public boolean isGItem(ItemStack itemStack){
        return registeredGItems.stream().anyMatch(gitem -> itemStack.isSimilar(gitem.getItemstack()));
    }
    public GItem getGItem(ItemStack itemStack){
        for (GItem gItem : registeredGItems){
            if (itemStack.isSimilar(gItem.getItemstack())){
                return gItem;
            }
        }
        return null;
    }
    public static GItemManager getInstance() {
        return instance;
    }


}
