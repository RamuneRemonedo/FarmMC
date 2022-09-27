package tokyo.ramune.farmmc.menu;

import tokyo.ramune.farmmc.utility.Chat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FarmMenuHandler {
    private static final List<FarmMenu> registeredMenu = new ArrayList<>();

    public static void registerMenu(@Nonnull FarmMenu menu) {
        if (registeredMenu.contains(menu)) return;
        registeredMenu.add(menu);
    }

    public static List<FarmMenu> getRegisteredMenus() {
        return registeredMenu;
    }

    @Nullable
    public static FarmMenu getRegisteredMenu(@Nonnull String title) {
        for (FarmMenu menu : registeredMenu) {
            if (menu.getTitle().equals(title))
                return menu;
        }
        return null;
    }

    public static boolean isFarmMenu(@Nonnull String title) {
        return title.startsWith(Chat.replaceColor("&r&a&r", '&'));
    }
}
