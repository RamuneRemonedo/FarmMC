package tokyo.ramune.farmmc.menu;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public interface Menu {

    Inventory getInventory();

    String getTitle();

    int getInventorySize();

    @Nonnull
    ArrayList<MenuItem> getMenuItemList();

    void onOpen(InventoryOpenEvent event);

    void onClose(InventoryCloseEvent event);
}