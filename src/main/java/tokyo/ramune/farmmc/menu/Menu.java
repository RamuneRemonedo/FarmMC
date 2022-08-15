package tokyo.ramune.farmmc.menu;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.HashMap;

public interface Menu {

    Inventory getInventory(FarmPlayer farmPlayer);

    String getTitle();

    HashMap<Integer, MenuItem> getMenuItemHashMap();

    void onOpen(InventoryOpenEvent event);

    void onClose(InventoryCloseEvent event);
}
