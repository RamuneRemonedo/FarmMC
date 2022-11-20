package tokyo.ramune.farmmc.game.cook;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CookHandler {
    @Nullable
    public static CookRecipe check(@Nonnull ItemStack... itemStacks) {
        Map<Material, Integer> itemAmount = toMap(itemStacks);

        for (CookRecipe recipe : CookRecipe.values()) {
            Map<Material, Integer> requireMaterials = getRequireMaterials(recipe);

            if (itemAmount.equals(requireMaterials))
                return recipe;
        }
        return null;
    }

    public static Map<Material, Integer> getRequireMaterials(@Nonnull CookRecipe recipe) {
        Map<Material, Integer> requireItems = new HashMap<>();

        for (CookMaterial cookMaterial : recipe.getMaterial()) {
            requireItems.put(cookMaterial.getMaterial(), cookMaterial.getAmount());
        }

        return requireItems;
    }

    public static Map<Material, Integer> toMap(ItemStack... itemStacks) {
        Map<Material, Integer> itemAmount = new HashMap<>();

        for (ItemStack itemStack : itemStacks) {
            if (itemAmount.containsKey(itemStack.getType())) {
                itemAmount.replace(itemStack.getType(), itemAmount.get(itemStack) + itemStack.getAmount());
            } else {
                itemAmount.put(itemStack.getType(), itemStack.getAmount());
            }
        }

        return itemAmount;
    }
}
