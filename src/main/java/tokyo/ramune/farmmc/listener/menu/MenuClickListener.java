package tokyo.ramune.farmmc.listener.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tokyo.ramune.farmmc.FarmMC;

public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        FarmMC.getMenuManager().onClick(event);
    }
}
