package tokyo.ramune.farmmc.core.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.item.CustomItem;
import tokyo.ramune.farmmc.core.language.Language;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.ItemStackBuilder;
import tokyo.ramune.farmmc.game.quest.QuestMenu;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ServerMenu extends Menu {
    private static Set<MenuItem> extensionMenuItem;

    public ServerMenu(@NotNull Player player) {
        super(player,
                LanguageHandler.getPhase(player, Phase.SERVER_MENU_TITLE),
                27,
                new MenuItem(viewer -> new ItemStack(Material.GRASS), 0, null));
    }

    public static void initialize() {
        extensionMenuItem = new HashSet<>();
    }

    public static void addExtensionMenuItem(@Nonnull MenuItem menuItem) {
        extensionMenuItem.add(menuItem);
    }

    public static CustomItem getServerMenuItem(@Nonnull Language language) {
        CustomItem item = new CustomItem("server_menu", Material.COMPASS, LanguageHandler.getPhase(language, Phase.SERVER_MENU_ITEM_TITLE));
        item.setLore(LanguageHandler.getPhase(language, Phase.SERVER_MENU_ITEM_DESCRIPTION));
        item.setOnClick(event -> new ServerMenu(event.getPlayer()).open());
        item.setAllowEnchantGlow(true);

        return item;
    }

    @Override
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
        ItemStack header = new ItemStackBuilder(Material.CYAN_STAINED_GLASS_PANE,
                ChatColor.RESET.toString(),
                null,
                false).build();

        for (int i = 0; i < 9; i++) {
            menuItems.add(new MenuItem(viewer -> header, i, null));
        }

        // Footer
        ItemStack footer = new ItemStackBuilder(Material.MAGENTA_STAINED_GLASS_PANE,
                ChatColor.RESET.toString(),
                null,
                false).build();

        for (int i = 18; i < 27; i++) {
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

        // Extension
        menuItems.addAll(extensionMenuItem);

        return menuItems.toArray(new MenuItem[0]);
    }

    @Override
    public void open() {
        super.open();
        getPlayer().playSound(getPlayer().getLocation(), Sound.BLOCK_CHEST_LOCKED, 1, 1);
    }
}

