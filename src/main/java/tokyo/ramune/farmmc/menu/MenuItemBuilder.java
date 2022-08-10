package tokyo.ramune.farmmc.menu;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.function.Consumer;
import java.util.function.Function;

public class MenuItemBuilder {
    private Function<FarmPlayer, ItemStack> item = farmPlayer -> new ItemStack(Material.GRASS_BLOCK);
    private int slot = 0;
    private Consumer<? super InventoryClickEvent> onClick = (event -> {});
    
    public MenuItemBuilder() {
    }

    public void setItem(Function<FarmPlayer, ItemStack> item) {
        this.item = item;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setOnClick(Consumer<? super InventoryClickEvent> onClick) {
        this.onClick = onClick;
    }

    public MenuItem build() {
        return new MenuItem() {
            @Override
            public ItemStack getItem(FarmPlayer farmPlayer) {
                return item.apply(farmPlayer);
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
    }
}
