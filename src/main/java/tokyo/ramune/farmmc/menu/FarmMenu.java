package tokyo.ramune.farmmc.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;

public class FarmMenu {
    private final String title;
    private final int size;
    private final FarmMenuItem[] menuItems;

    public FarmMenu(@Nonnull String title, int size, FarmMenuItem[] menuItems) {
        this.title = title;
        this.size = size;
        this.menuItems = menuItems;
    }

    public void register() {
        FarmMenuHandler.registerMenu(this);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public FarmMenuItem[] getMenuItems() {
        return menuItems;
    }

    public void open(@Nonnull Player player) throws IllegalStateException {
        if (!FarmMenuHandler.getRegisteredMenus().contains(this)) {
            throw new IllegalStateException(
                    "Cannot open this menu." +
                            " it has not registered in tokyo.ramune.farmmc.menu.FarmMenuHandler class yet.");
        }

        Inventory menu = Bukkit.createInventory(
                null,
                size,
                Component.text(ChatColor.RESET + ChatColor.GREEN.toString() + ChatColor.RESET + title)
        );

        for (FarmMenuItem menuItem : menuItems) {
            menu.setItem(menuItem.getSlot(), menuItem.getItemStack());
        }

        player.openInventory(menu);
    }
}
