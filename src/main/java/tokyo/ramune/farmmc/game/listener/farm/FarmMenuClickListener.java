package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuHandler;
import tokyo.ramune.farmmc.core.menu.MenuItem;

import java.util.Objects;

public class FarmMenuClickListener implements Listener {
    @EventHandler
    public void onFarmMenuClick(InventoryClickEvent event) {
        InventoryView view = event.getView();

        if (!MenuHandler.isFarmMenu(view.getTitle()))
            return;

        if (event.getCurrentItem() == null)
            return;

        ItemStack clickedItem = event.getCurrentItem();

        Menu menu = Objects.requireNonNull(MenuHandler.getRegisteredMenu(view.getTitle()));
        event.setCancelled(true);
        for (MenuItem menuItem : menu.getMenuItems()) {
            if (menuItem.getItemStack().equals(clickedItem))
                menuItem.onClick(event);
        }
    }
}
