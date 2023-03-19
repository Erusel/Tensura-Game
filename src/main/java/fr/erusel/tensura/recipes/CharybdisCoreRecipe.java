package fr.erusel.tensura.recipes;

import fr.erusel.tensura.enums.GItems;
import fr.erusel.tensura.objects.GRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

public class CharybdisCoreRecipe extends GRecipe {

    @Override
    public ShapelessRecipe getShapelessRecipe() {
        return null;
    }

    @Override
    public ShapelessRecipe getShapedRecipe() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(getMain(), "charbydisCore"), GItems.CHARYBDIS_CORE.createInstance().getItemstack());
        recipe.addIngredient(new RecipeChoice.ExactChoice(GItems.CHARYBDIS_PART_ONE.createInstance().getItemstack()));
        recipe.addIngredient(new RecipeChoice.ExactChoice(GItems.CHARYBDIS_PART_TWO.createInstance().getItemstack()));
        recipe.addIngredient(new RecipeChoice.ExactChoice(GItems.CHARYBDIS_PART_THREE.createInstance().getItemstack()));
        return recipe;
    }

}
