package tokyo.ramune.farmmc.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Function;

public class MenuItem {
    private final Function<Player, ItemStack> itemStack;
    private final int slot;
    private final Consumer<InventoryClickEvent> onClick;

    public MenuItem(@Nonnull Function<Player, ItemStack> itemStack, int slot, Consumer<InventoryClickEvent> onClick) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.onClick = onClick;
    }

    public ItemStack getItemStack(@Nonnull Player player) {
        return itemStack.apply(player);
    }

    public int getSlot() {
        return slot;
    }

    public void onClick(InventoryClickEvent event) {
        onClick.accept(event);
    }
}
