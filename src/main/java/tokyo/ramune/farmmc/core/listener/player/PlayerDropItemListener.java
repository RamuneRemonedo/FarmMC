package tokyo.ramune.farmmc.core.listener.player;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.item.CustomItem;
import tokyo.ramune.farmmc.core.item.CustomItemHandler;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;

public class PlayerDropItemListener implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem = event.getItemDrop().getItemStack();

        CustomItem item = CustomItemHandler.toCustomItem(droppedItem);

        if (item == null)
            return;

        if (item.getId().equals("server_menu")) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1F, 1F);
            Chat.sendMessage(player, LanguageHandler.getPhase(player, Phase.SERVER_MENU_ITEM_DROPPED_WARNING), true, true);
        }
    }
}
