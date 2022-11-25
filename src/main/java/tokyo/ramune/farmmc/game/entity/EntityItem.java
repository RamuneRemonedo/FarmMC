package tokyo.ramune.farmmc.game.entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum EntityItem {
    HELMET(new ItemStack(Material.LEATHER_HELMET));

    private final ItemStack itemStack;

    EntityItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
