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
import tokyo.ramune.farmmc.core.util.ItemStackBuilder;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class QuestMenu {
    private final Menu menu;
    private final Player player;

    public QuestMenu(@Nonnull Player player) {
        this.player = player;

        String title = LanguageHandler.getPhase(player, Phase.QUEST_MENU_TITLE);
        int size = 54;

        menu = new Menu(player, title, size, getMenuItems());
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuItem[] getMenuItems() {
        List<MenuItem> menuItems = new LinkedList<>();

        // Fill background with gray stained-glass pane
        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta backgroundMeta = background.getItemMeta();

        backgroundMeta.displayName(Component.text(ChatColor.RESET.toString()));
        background.setItemMeta(backgroundMeta);

        for (int i = 0; i < 54; i++) {
            menuItems.add(new MenuItem(viewer -> background, i, null));
        }

        // Set quests item
        int i = 0;
        for (Quest quest : Quest.values()) {
            menuItems.add(new MenuItem(viewer -> {
                boolean addEnchant = !quest.isGranted(player) && quest.getRequireQuest() != null && quest.getRequireQuest().isGranted(player);
                return new ItemStackBuilder(
                        quest.getIcon(),
                        quest.getTitle().apply(LanguageHandler.getLanguage(player)),
                        null,
                        addEnchant
                ).build();
            }, i, null));

            i++;
        }

        return menuItems.toArray(new MenuItem[0]);
    }
}
