package tokyo.ramune.farmmc.listener.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import tokyo.ramune.farmmc.FarmMC;

public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView inventory = event.getView();

        System.out.println(event.getCursor().getType());
        if (!FarmMC.getMenuManager().isFarmMCMenu(inventory)) return;
        if (event.getCurrentItem() == null) return;
    }
}
