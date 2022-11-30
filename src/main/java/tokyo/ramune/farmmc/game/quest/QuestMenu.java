package tokyo.ramune.farmmc.game.quest;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuItem;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class QuestMenu {
    private final Menu menu;
    private final Player player;

    public QuestMenu(@Nonnull Player player) {
        this.player = player;

        String title = LanguageHandler.getPhase(player, Phase.QUEST_MENU_TITLE);
        int size = 54;

        menu = new Menu(player, title, size, getMenuItems());
    }

    public MenuItem[] getMenuItems() {
        Set<MenuItem> menuItems = new HashSet<>();

        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta backgroundMeta = background.getItemMeta();

        backgroundMeta.displayName(Component.text(ChatColor.RESET.toString()));
        background.setItemMeta(backgroundMeta);

        for (int i = 0; i < 54; i++) {
            menuItems.add(new MenuItem(targetPlayer -> background, i, null));
        }

        for (Quest quest : Quest.values()) {

        }
        return null;
    }
}
