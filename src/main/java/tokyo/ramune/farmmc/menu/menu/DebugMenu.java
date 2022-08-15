package tokyo.ramune.farmmc.menu.menu;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.farmmc.menu.Menu;
import tokyo.ramune.farmmc.menu.MenuItem;
import tokyo.ramune.farmmc.menu.menuItem.BackgroundMenuItem;
import tokyo.ramune.farmmc.menu.menuItem.CloseMenuItem;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.HashMap;

public class DebugMenu implements Menu {
    @Override
    public Inventory getInventory(FarmPlayer farmPlayer) {
        Inventory inv = Bukkit.createInventory(null, 27, getTitle());
        getMenuItemHashMap().forEach((slot, menuItem) -> inv.setItem(slot, menuItem.getItem(farmPlayer)));
        return inv;
    }

    @Override
    public String getTitle() {
        return "Debug Menu";
    }

    @Override
    public HashMap<Integer, MenuItem> getMenuItemHashMap() {
        HashMap<Integer, MenuItem> menuItemHashMap = new HashMap<>();

        for (int i = 0; i < 27; i++) {
            menuItemHashMap.put(i, new BackgroundMenuItem());
        }
        menuItemHashMap.replace(22, new CloseMenuItem());
        return menuItemHashMap;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        if (!event.getPlayer().isOp()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You must be OP on this server to open this menu.");
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
    }
}
