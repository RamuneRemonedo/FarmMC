package tokyo.ramune.farmmc.game.entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum FarmEntityItem {
    HELMET(new ItemStack(Material.LEATHER_HELMET));

    private final ItemStack itemStack;

    FarmEntityItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
