package tokyo.ramune.farmmc.core.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.ItemStackBuilder;
import tokyo.ramune.farmmc.game.quest.QuestMenu;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class ServerMenu {
    private final Menu menu;

    public ServerMenu(@Nonnull Player player) {
        String title = LanguageHandler.getPhase(player, Phase.SERVER_MENU_TITLE);
        int size = 27;

        menu = new Menu(player, title, size, getMenuItems());
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuItem[] getMenuItems() {
        List<MenuItem> menuItems = new LinkedList<>();

        // Fill background with gray stained-glass pane
        ItemStack background = new ItemStackBuilder(Material.GRAY_STAINED_GLASS_PANE,
                ChatColor.RESET.toString(),
                null,
                false).build();

        for (int i = 0; i < 27; i++) {
            menuItems.add(new MenuItem(viewer -> background, i, null));
        }

        // Header
        ItemStack header = new ItemStackBuilder(Material.BLACK_STAINED_GLASS_PANE,
                ChatColor.RESET.toString(),
                null,
                false).build();

        for (int i = 0; i < 8; i++) {
            menuItems.add(new MenuItem(viewer -> header, i, null));
        }

        // Footer
        ItemStack footer = new ItemStackBuilder(Material.BROWN_STAINED_GLASS_PANE,
                ChatColor.RESET.toString(),
                null,
                false).build();

        for (int i = 19; i < 27; i++) {
            menuItems.add(new MenuItem(viewer -> footer, i, null));
        }

        menuItems.add(
                new MenuItem(
                        viewer -> new ItemStackBuilder(Material.BARRIER,
                                LanguageHandler.getPhase(viewer, Phase.MENU_CLOSE_ITEM_TITLE),
                                LanguageHandler.getPhase(viewer, Phase.MENU_CLOSE_ITEM_DESCRIPTION),
                                false).build(),
                        22,
                        event -> event.getWhoClicked().closeInventory()));

        // Lobby server
        menuItems.add(
                new MenuItem(
                        viewer -> new ItemStackBuilder(Material.BIRCH_DOOR,
                                LanguageHandler.getPhase(viewer, Phase.SERVER_MENU_ITEM_TITLE)
                                        .replace("{0}", LanguageHandler.getPhase(viewer, Phase.SERVER_LOBBY)),
                                LanguageHandler.getPhase(viewer, Phase.SERVER_MENU_ITEM_DESCRIPTION)
                                        .replace("{0}", LanguageHandler.getPhase(viewer, Phase.SERVER_LOBBY)),
                                false).build(),
                        10,
                        event -> new QuestMenu((Player) event.getWhoClicked()).getMenu().open()));

        return menuItems.toArray(new MenuItem[0]);
    }
}
