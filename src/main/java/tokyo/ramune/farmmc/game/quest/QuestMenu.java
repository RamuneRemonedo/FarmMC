package tokyo.ramune.farmmc.game.quest;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuItem;
import tokyo.ramune.farmmc.core.menu.ServerMenu;
import tokyo.ramune.farmmc.core.util.ItemStackBuilder;

import java.util.LinkedList;
import java.util.List;

public class QuestMenu extends Menu {
    private boolean allowHideCompleted = true;
    private boolean allowCurrentQuestFilter = false;

    public QuestMenu(@NotNull Player player) {
        super(player, LanguageHandler.getPhase(player, Phase.QUEST_MENU_TITLE), 54, new MenuItem(viewer -> new ItemStack(Material.GRASS), 0, null));
    }

    @Override
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

        for (int i = 45; i < 54; i++) {
            menuItems.add(new MenuItem(viewer -> footer, i, null));
        }
        menuItems.add(
                new MenuItem(
                        viewer -> new ItemStackBuilder(Material.BARRIER,
                                LanguageHandler.getPhase(viewer, Phase.MENU_CLOSE_ITEM_TITLE),
                                LanguageHandler.getPhase(viewer, Phase.MENU_CLOSE_ITEM_DESCRIPTION),
                                false).build(),
                        49,
                        event -> event.getWhoClicked().closeInventory()));

        menuItems.add(
                new MenuItem(
                        viewer -> new ItemStackBuilder(Material.ARROW,
                                LanguageHandler.getPhase(viewer, Phase.MENU_RETURN_ITEM_TITLE),
                                LanguageHandler.getPhase(viewer, Phase.MENU_RETURN_ITEM_DESCRIPTION),
                                false).build(),
                        48,
                        event -> new ServerMenu((Player) event.getWhoClicked()).open()
                )
        );

        // Set quests item
        int i = 9;
        for (Quest quest : Quest.values()) {
            if (allowHideCompleted && quest.isGranted(getPlayer()))
                continue;

            Quest currentQuest = QuestHandler.getCurrentQuest(getPlayer());
            if (allowCurrentQuestFilter && currentQuest != null && currentQuest.equals(quest))
                continue;

            menuItems.add(new MenuItem(viewer -> {
                boolean addEnchant = !quest.isGranted(getPlayer()) && quest.getRequireQuest() != null && quest.getRequireQuest().isGranted(getPlayer());
                return new ItemStackBuilder(
                        quest.getIcon(),
                        quest.getTitle().apply(LanguageHandler.getLanguage(getPlayer())),
                        null,
                        addEnchant
                ).build();
            }, i, null));

            i++;
        }

        return menuItems.toArray(new MenuItem[0]);
    }

    @Override
    public void open() {
        super.open();
        getPlayer().playSound(getPlayer().getLocation(), Sound.BLOCK_CHEST_LOCKED, 1, 0);
    }
}
