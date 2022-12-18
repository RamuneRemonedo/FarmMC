package tokyo.ramune.farmmc.core.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.item.CustomItem;
import tokyo.ramune.farmmc.core.item.CustomItemHandler;

import java.util.Objects;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        EquipmentSlot hand = event.getHand();

        if (Objects.equals(hand, EquipmentSlot.OFF_HAND)) return;

        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        CustomItem customItem = CustomItemHandler.toCustomItem(itemInMainHand);

        if (customItem == null)
            return;

        customItem.getOnClick().accept(event);
    }
}
