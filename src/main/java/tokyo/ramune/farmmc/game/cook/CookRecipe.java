package tokyo.ramune.farmmc.game.cook;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public enum CookRecipe {
    TEA(new ItemStack(Material.LAVA_BUCKET),
            new CookMaterial(Material.GRASS, 1, null),
            new CookMaterial(Material.WATER_BUCKET, 1, null)
    );

    private final ItemStack result;
    private final Set<CookMaterial> material;

    CookRecipe(ItemStack result, CookMaterial... cookMaterial) {
        this.result = result;
        this.material = Set.of(cookMaterial);
    }

    public ItemStack getResult() {
        return result;
    }

    public Set<CookMaterial> getMaterial() {
        return material;
    }
}
