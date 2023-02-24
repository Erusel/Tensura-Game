package fr.erusel.tensura.enums;

import fr.erusel.tensura.items.charybdis.CharybdisPartOneItem;
import fr.erusel.tensura.items.charybdis.CharybdisPartThreeItem;
import fr.erusel.tensura.items.charybdis.CharybdisPartTwoItem;
import fr.erusel.tensura.objects.GItem;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public enum GItems {

    CHARYBDIS_PART_ONE(CharybdisPartOneItem::new),
    CHARYBDIS_PART_TWO(CharybdisPartTwoItem::new),
    CHARYBDIS_PART_THREE(CharybdisPartThreeItem::new);


    private final Supplier<GItem> supplier;

    GItems(Supplier<GItem> supplier) {
        this.supplier = supplier;
    }

    public GItem createInstance(){
        return supplier.get();
    }

    public static GItems getGItem(ItemStack itemStack){
        for (GItems gItems : GItems.values()){
            if (gItems.createInstance().getItemstack().isSimilar(itemStack)){
                return gItems;
            }
        }
        return null;
    }



}
