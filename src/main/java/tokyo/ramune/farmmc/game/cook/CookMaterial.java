package tokyo.ramune.farmmc.game.cook;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CookMaterial {
    private final Material material;
    private final int amount;
    private final ItemStack returnItem;

    public CookMaterial(@Nonnull Material material, int amount, @Nullable ItemStack returnItem) {
        this.material = material;
        this.amount = amount;
        this.returnItem = returnItem;
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    @Nullable
    public ItemStack getReturnItem() {
        return returnItem;
    }
}
