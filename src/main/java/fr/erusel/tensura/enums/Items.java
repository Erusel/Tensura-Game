package fr.erusel.tensura.enums;

import fr.erusel.tensura.items.charybdis.CharybdisPartOneItem;
import fr.erusel.tensura.items.charybdis.CharybdisPartThreeItem;
import fr.erusel.tensura.items.charybdis.CharybdisPartTwoItem;
import fr.erusel.tensura.objects.GItem;

import java.lang.reflect.InvocationTargetException;

public enum Items {

    CHARYBDISPARTONE(CharybdisPartOneItem.class),
    CHARYBDISPARTTWO(CharybdisPartTwoItem.class),
    CHARYBDISPARTTHREE(CharybdisPartThreeItem.class);


    private final Class<? extends GItem> itemClass;

    Items(Class itemClass) {
        this.itemClass = itemClass;
    }
    public Class<? extends GItem> getItemClass(){
        return itemClass;
    }

    public GItem createInstance(){
        try {
            return itemClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
