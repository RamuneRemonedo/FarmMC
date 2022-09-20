package tokyo.ramune.farmmc.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class FarmMenuItem {
    private final ItemStack itemStack;
    private final int slot;
    private final Consumer<InventoryClickEvent> onClick;

    public FarmMenuItem(@Nonnull ItemStack itemStack, int slot, Consumer<InventoryClickEvent> onClick) {
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
