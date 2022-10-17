package tokyo.ramune.farmmc.utility;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import tokyo.ramune.farmmc.FarmMC;

import javax.annotation.Nonnull;
import java.util.Map;

public class RecipeBuilder {
    private final ShapedRecipe recipe;

    public RecipeBuilder(@Nonnull String id,
                         @Nonnull ItemStack resultItem,
                         @Nonnull Map<Character, RecipeChoice> ingredient,
                         @Nonnull String... shape) {
        this.recipe = new ShapedRecipe(new NamespacedKey(FarmMC.getPlugin(), id), resultItem);

        recipe.shape(shape);
        ingredient.forEach(recipe::setIngredient);
    }

    public ShapedRecipe build() {
        return recipe;
    }
}
