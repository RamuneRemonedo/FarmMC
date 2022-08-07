package tokyo.ramune.farmmc.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MenuItemBuilder {
    private ItemStack item = new ItemStack(Material.GRASS_BLOCK);
    private int slot = 0;
    private Consumer<? super InventoryClickEvent> onClick = (event -> {});
    
    public MenuItemBuilder() {
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setOnClick(Consumer<? super InventoryClickEvent> onClick) {
        this.onClick = onClick;
    }

    public Consumer<? super InventoryClickEvent> getOnClick() {
        return onClick;
    }

    public MenuItem build() {
        MenuItem menuItem = new MenuItem() {
            @Override
            public ItemStack getItem() {
                return item;
            }

            @Override
            public int getSlot() {
                return slot;
            }

            @Override
            public void onClick(InventoryClickEvent event) {
                onClick.accept(event);
            }
        };
        return menuItem;
    }
}
