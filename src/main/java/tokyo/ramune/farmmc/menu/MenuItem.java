package tokyo.ramune.farmmc.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface MenuItem {

    ItemStack getItem();

    int getSlot();

    void onClick(InventoryClickEvent event);
}
