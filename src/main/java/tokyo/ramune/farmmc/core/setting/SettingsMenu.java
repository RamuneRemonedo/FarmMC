package tokyo.ramune.farmmc.core.setting;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuItem;
import tokyo.ramune.farmmc.core.util.Chat;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SettingsMenu {
    private final Menu menu;

    public SettingsMenu(@Nonnull Player player) {
        List<MenuItem> settingsItem = new ArrayList<>();

        int i = 0;
        for (CoreSettingHandler setting : CoreSettingHandler.values()) {
            if (!(setting.getDefaultValue() instanceof Boolean))
                continue;

            settingsItem.add(new MenuItem(targetPlayer -> getMenuItem(setting, player), i, event -> onClick(setting, event)));
            i++;
        }

        menu = new Menu(player, LanguageHandler.getPhase(player, Phase.MENU_SETTINGS_TITLE), 27, settingsItem.toArray(new MenuItem[0]));
    }

    public ItemStack getMenuItem(CoreSettingHandler setting, Player player) {
        if (!(setting.getDefaultValue() instanceof Boolean))
            return new ItemStack(Material.GRASS);

        ItemStack item = new ItemStack(setting.getIcon());
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text(LanguageHandler.getPhase(player, setting.getTitle())));

        List<Component> lore = new ArrayList<>();
        for (String s : StringUtils.split(LanguageHandler.getPhase(player, setting.getDescription()), "\n")) {
            lore.add(Component.text(s));
        }

        if (setting.getData(player.getUniqueId()).getAsBoolean()) {
            lore.add(Component.text(Chat.replaceColor("有効: &a✓", '&')));
        } else {
            lore.add(Component.text(Chat.replaceColor("有効: &c✗", '&')));
        }

        meta.lore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public Menu getMenu() {
        return menu;
    }

    private void onClick(CoreSettingHandler setting, InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        setting.set(player.getUniqueId(), !setting.getData(player.getUniqueId()).getAsBoolean());
        player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);

        player.closeInventory();
        CoreSettingHandler.update(setting, player);
        new SettingsMenu(player).getMenu().open();
    }
}
