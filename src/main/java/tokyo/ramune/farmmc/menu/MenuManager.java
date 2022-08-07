package tokyo.ramune.farmmc.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class MenuManager {

    private final ArrayList<Menu> menus = new ArrayList<>();

    public MenuManager() {
    }

    public void onClick(InventoryClickEvent event) {
        Menu menu = getMenu(event.getInventory());
        if (menu == null) return;
        event.setCancelled(true);
        if (event.getCurrentItem() == null) return;
        menu.getMenuItemList().forEach((menuItem -> {
            if (menuItem.getSlot() == event.getSlot()
                    && menuItem.getItem().equals(event.getCurrentItem())) menuItem.onClick(event);
        }));
    }

    @Nullable
    public Menu getMenu(Inventory inventory) {
        for (Menu menu : menus) {
            if (menu.getInventory().equals(inventory)) return menu;
        }
        return null;
    }
}
