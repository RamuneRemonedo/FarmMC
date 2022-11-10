package tokyo.ramune.farmmc.core.listener.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tokyo.ramune.farmmc.core.menu.MenuHandler;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (MenuHandler.isRegistered(event.getView().getTitle(), player))
            MenuHandler.unregisterMenu(MenuHandler.getRegisteredMenu(event.getView().getTitle(), player));
    }
}
