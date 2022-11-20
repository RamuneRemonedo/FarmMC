package tokyo.ramune.farmmc.core.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.farmmc.core.util.Chat;

import javax.annotation.Nonnull;

public class Menu {
    private final Player player;
    private final String title;
    private final int size;
    private final MenuItem[] menuItems;

    public Menu(@Nonnull Player player, @Nonnull String title, int size, MenuItem... menuItems) {
        this.player = player;
        this.title = title;
        this.size = size;
        this.menuItems = menuItems;
    }

    public void register() {
        MenuHandler.registerMenu(this);
    }

    public Player getPlayer() {
        return player;
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

    public void open() {
        register();

        Inventory menu = Bukkit.createInventory(
                null,
                size,
                Component.text(Chat.replaceColor("&r&a&r" + title, '&'))
        );

        for (MenuItem menuItem : menuItems) {
            menu.setItem(menuItem.getSlot(), menuItem.getItemStack(player));
        }

        player.openInventory(menu);
    }
}
