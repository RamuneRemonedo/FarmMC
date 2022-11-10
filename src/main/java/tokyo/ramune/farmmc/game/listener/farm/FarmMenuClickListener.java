package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.entity.Player;
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
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();

        Menu menu = MenuHandler.getRegisteredMenu(view.getTitle(), player);

        if (menu == null)
            return;

        if (event.getCurrentItem() == null)
            return;


        event.setCancelled(true);


        ItemStack clickedItem = event.getCurrentItem();

        for (MenuItem menuItem : menu.getMenuItems()) {
            if (menuItem.getItemStack(menu.getPlayer()).equals(clickedItem))
                menuItem.onClick(event);
        }
    }
}
