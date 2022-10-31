package tokyo.ramune.farmmc.core.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class MenuItem {
    private final ItemStack itemStack;
    private final int slot;
    private final Consumer<InventoryClickEvent> onClick;

    public MenuItem(@Nonnull ItemStack itemStack, int slot, Consumer<InventoryClickEvent> onClick) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.onClick = onClick;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public void onClick(InventoryClickEvent event) {
        onClick.accept(event);
    }
}
