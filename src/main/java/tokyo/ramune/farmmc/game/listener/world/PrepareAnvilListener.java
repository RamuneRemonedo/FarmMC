package tokyo.ramune.farmmc.game.listener.world;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;

import java.util.List;
import java.util.Objects;

public class PrepareAnvilListener implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        Player player = (Player) event.getViewers().get(0);

        if (!Objects.requireNonNull(inventory.getRenameText()).isEmpty()) {
            ItemStack fakeItem = new ItemStack(Material.BARRIER);
            ItemMeta meta = fakeItem.getItemMeta();

            meta.displayName(Component.text(Chat.replaceColor("&c&lRenaming is disabled.", '&')));
            meta.setLore(List.of(LanguageHandler.getPhase(player, Phase.ANVIL_DENY_RENAMING)));

            fakeItem.setItemMeta(meta);
            event.setResult(fakeItem);
        }
    }
}
