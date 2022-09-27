package tokyo.ramune.farmmc.listener.farm;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.menu.FarmMenu;
import tokyo.ramune.farmmc.menu.FarmMenuHandler;
import tokyo.ramune.farmmc.menu.FarmMenuItem;

import java.util.Objects;

public class FarmMenuClickListener implements Listener {
    @EventHandler
    public void onFarmMenuClick(InventoryClickEvent event) {
        InventoryView view = event.getView();

        if (!FarmMenuHandler.isFarmMenu(view.getTitle()))
            return;

        if (event.getCurrentItem() == null)
            return;

        ItemStack clickedItem = event.getCurrentItem();

        FarmMenu menu = Objects.requireNonNull(FarmMenuHandler.getRegisteredMenu(view.getTitle()));
        event.setCancelled(true);
        for (FarmMenuItem menuItem : menu.getMenuItems()) {
            if (menuItem.getItemStack().equals(clickedItem))
                menuItem.onClick(event);
        }
    }
}
