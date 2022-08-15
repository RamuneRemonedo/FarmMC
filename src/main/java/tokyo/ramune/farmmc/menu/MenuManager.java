package tokyo.ramune.farmmc.menu;

import org.bukkit.ChatColor;
import org.bukkit.inventory.InventoryView;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class MenuManager {

    private final ArrayList<Menu> menus = new ArrayList<>();
    private final ArrayList<MenuItem> menuItems = new ArrayList<>();

    public MenuManager() {
    }

    public void registerMenu(Menu... menus) {
        for (Menu menu : menus) {
            if (this.menus.contains(menu)) continue;
            this.menus.add(menu);
        }
    }

    public void registerMenuItem(MenuItem... menuItems) {
        for (MenuItem menuItem : menuItems) {
            if (this.menuItems.contains(menuItem)) return;
            this.menuItems.add(menuItem);
        }
    }

    @Nullable
    public Menu getMenu(String title) {
        for (Menu menu : menus) {
            if (menu.getTitle().equals(title)) return menu;
        }
        return null;
    }

    public boolean isFarmMCMenu(InventoryView inventoryView) {
        return inventoryView.getTitle()
                .startsWith(ChatColor.GOLD + ChatColor.YELLOW.toString() + ChatColor.RESET);
    }
}
