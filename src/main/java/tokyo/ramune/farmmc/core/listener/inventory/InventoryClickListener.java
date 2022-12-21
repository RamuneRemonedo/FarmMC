package tokyo.ramune.farmmc.core.listener.inventory;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuHandler;
import tokyo.ramune.farmmc.core.menu.MenuItem;

public class InventoryClickListener implements Listener {
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

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Location location = player.getLocation();

        player.playSound(location, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 0.3F, 2);
    }
}
