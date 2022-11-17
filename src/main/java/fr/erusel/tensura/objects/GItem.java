package fr.erusel.tensura.objects;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class GItem {

    private final String name;
    private final String lore;
    private final Material material;
    private final ItemStack itemStack;

    public GItem(String name, String lore, Material material) {
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.itemStack = new ItemBuilder(getMaterial()).name(getName()).addLore(getLore()).build();
    }

    public abstract void onUse();

    public String getName(){
        return name;
    }
    public String getLore(){
        return lore;
    }
    public Material getMaterial(){
        return material;
    }
    public ItemStack getItemstack(){
        return itemStack;
    }
}
