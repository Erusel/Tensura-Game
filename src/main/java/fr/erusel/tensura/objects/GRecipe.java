package fr.erusel.tensura.objects;

import org.bukkit.inventory.ShapelessRecipe;

public abstract class GRecipe extends GameElement{

    public abstract ShapelessRecipe getShapelessRecipe();
    public abstract ShapelessRecipe getShapedRecipe();
}
