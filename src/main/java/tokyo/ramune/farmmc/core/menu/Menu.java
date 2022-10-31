package tokyo.ramune.farmmc.core.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.farmmc.core.utility.Chat;

import javax.annotation.Nonnull;

public class Menu {
    private final String title;
    private final int size;
    private final MenuItem[] menuItems;

    public Menu(@Nonnull String title, int size, MenuItem[] menuItems) {
        this.title = title;
        this.size = size;
        this.menuItems = menuItems;
    }

    public void register() {
        MenuHandler.registerMenu(this);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void open(@Nonnull Player player) throws IllegalStateException {
        if (!MenuHandler.getRegisteredMenus().contains(this)) {
            throw new IllegalStateException(
                    "Cannot open this menu." +
                            " it has not registered in tokyo.ramune.farmmc.core.menu.MenuHandler class yet.");
        }

        Inventory menu = Bukkit.createInventory(
                null,
                size,
                Component.text(Chat.replaceColor("&r&a&r" + title, '&'))
        );

        for (MenuItem menuItem : menuItems) {
            menu.setItem(menuItem.getSlot(), menuItem.getItemStack());
        }

        player.openInventory(menu);
    }
}
