package tokyo.ramune.farmmc.game.cook;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuItem;

import java.util.*;

public class CookMenu {
    private final Menu menu;
    private final Player player;

    private Map<Material, Integer> putMaterials = new HashMap<>();

    public CookMenu(@NotNull Player player) {
        this.player = player;

        String title = LanguageHandler.getPhase(player, Phase.COOK_MENU_TITLE);
        int size = 27;

        menu = new Menu(player, title, size, getMenuItems());
    }

    public MenuItem[] getMenuItems() {
        Set<MenuItem> menuItems = new HashSet<>();

        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta backgroundMeta = background.getItemMeta();

        backgroundMeta.displayName(Component.text(ChatColor.RESET.toString()));
        background.setItemMeta(backgroundMeta);

        for (int i = 0; i < 27; i++) {
            menuItems.add(new MenuItem(targetPlayer -> background, i, (event) -> update()));
        }

        if (new Random().nextBoolean()) {
            MenuItem menuItemq = null;
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getSlot() == 13)
                    menuItemq = menuItem;
            }
            menuItems.remove(menuItemq);
            menuItems.add(new MenuItem(targetPlayer -> new ItemStack(Material.RED_WOOL), 13, (event -> update())));
        } else {
            MenuItem menuItemq = null;
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getSlot() == 13)
                    menuItemq = menuItem;
            }
            menuItems.remove(menuItemq);
            menuItems.add(new MenuItem(targetPlayer -> new ItemStack(Material.BLUE_WOOL), 13, (event -> update())));
        }

        return menuItems.toArray(new MenuItem[0]);
    }

    public void update() {
        InventoryView inv = player.getOpenInventory();
        for (int i = 0; i < 27; i++) {
            inv.setItem(1, new ItemStack(Material.AIR));
        }
        for (MenuItem menuItem : getMenuItems()) {
            player.getOpenInventory().setItem(menuItem.getSlot(), menuItem.getItemStack(player));
        }
    }

    public void mix() {


        clearPutMaterials();
    }

    public void clearPutMaterials() {
        putMaterials = new HashMap<>();
    }

    public Menu getMenu() {
        return menu;
    }
}
