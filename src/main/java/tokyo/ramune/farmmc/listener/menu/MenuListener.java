package tokyo.ramune.farmmc.listener.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryView;
import tokyo.ramune.farmmc.FarmMC;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView inventory = event.getView();

        if (!FarmMC.getMenuManager().isFarmMCMenu(inventory)) return;
        if (event.getCurrentItem() == null) return;

    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        InventoryView inventory = event.getView();

        if (!FarmMC.getMenuManager().isFarmMCMenu(inventory)) return;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryView inventory = event.getView();

        if (!FarmMC.getMenuManager().isFarmMCMenu(inventory)) return;
    }
}
