package tokyo.ramune.farmmc.core.menu;

import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.util.Chat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MenuHandler {
    private static final List<Menu> registeredMenu = new ArrayList<>();

    public static void registerMenu(@Nonnull Menu menu) {
        if (registeredMenu.contains(menu)) return;
        registeredMenu.add(menu);
    }

    public static void unregisterMenu(@Nonnull Menu menu) {
        registeredMenu.remove(menu);
    }

    public static List<Menu> getRegisteredMenus() {
        return registeredMenu;
    }

    @Nullable
    public static Menu getRegisteredMenu(@Nonnull String title, @Nonnull Player player) {
        for (Menu menu : registeredMenu) {
            if (Chat.replaceColor("&r&a&r" + menu.getTitle(), '&').equals(title) && menu.getPlayer().equals(player))
                return menu;
        }
        return null;
    }

    public static boolean isRegistered(@Nonnull String title, @Nonnull Player player) {
        for (Menu menu : registeredMenu) {
            if (menu.getTitle().equals(title) && menu.getPlayer().equals(player))
                return true;
        }
        return false;
    }

    public static boolean isFarmMenu(@Nonnull String title) {
        return title.startsWith(Chat.replaceColor("&r&a&r", '&'));
    }
}
