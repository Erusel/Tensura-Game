package fr.erusel.tensura.enums;

import fr.erusel.tensura.items.charybdis.CharybdisPartOneItem;
import fr.erusel.tensura.items.charybdis.CharybdisPartThreeItem;
import fr.erusel.tensura.items.charybdis.CharybdisPartTwoItem;
import fr.erusel.tensura.objects.GItem;

import java.util.function.Supplier;

public enum Items {

    CHARYBDISPARTONE(CharybdisPartOneItem::new),
    CHARYBDISPARTTWO(CharybdisPartTwoItem::new),
    CHARYBDISPARTTHREE(CharybdisPartThreeItem::new);


    private final Supplier<GItem> itemSupplier;

    Items(Supplier<GItem> itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    public GItem createInstance(){
        return itemSupplier.get();
    }
}
