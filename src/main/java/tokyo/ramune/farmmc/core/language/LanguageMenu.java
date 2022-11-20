package tokyo.ramune.farmmc.core.language;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuItem;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class LanguageMenu {
    //private final Menu menu;
    private final Player player;

    public LanguageMenu(@Nonnull Player player) {
        this.player = player;

        List<MenuItem> menuItems = new ArrayList<>();

        for (String supportedLangCode : LanguageHandler.getSupportedLangCodes()) {

        }
    }

    public ItemStack getMenuItem(@Nonnull String languageCode) {
        return null; // TODO: 2022/11/18
    }
}
