package tokyo.ramune.farmmc.core.menu;

import tokyo.ramune.farmmc.core.utility.Chat;

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

    public static List<Menu> getRegisteredMenus() {
        return registeredMenu;
    }

    @Nullable
    public static Menu getRegisteredMenu(@Nonnull String title) {
        for (Menu menu : registeredMenu) {
            if (menu.getTitle().equals(title))
                return menu;
        }
        return null;
    }

    public static boolean isFarmMenu(@Nonnull String title) {
        return title.startsWith(Chat.replaceColor("&r&a&r", '&'));
    }
}