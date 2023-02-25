package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.GRecipe;
import fr.erusel.tensura.recipes.CharybdisCoreRecipe;

import java.util.function.Supplier;

public enum GRecipes {

    CHARYBDIS_CORE(CharybdisCoreRecipe::new);


    private final Supplier<GRecipe> supplier;

    GRecipes(Supplier<GRecipe> supplier) {
        this.supplier = supplier;
    }

    public GRecipe createInstance(){
        return supplier.get();
    }


}
