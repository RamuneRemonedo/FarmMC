package tokyo.ramune.farmmc.core.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import tokyo.ramune.farmmc.core.item.CustomItemHandler;

import java.util.Objects;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        EquipmentSlot hand = event.getHand();

        if (Objects.equals(hand, EquipmentSlot.OFF_HAND)) return;

        try {
            Objects.requireNonNull(CustomItemHandler.toCustomItem(Objects.requireNonNull(event.getItem()))).getOnClick().accept(event);
        } catch (NullPointerException ignored) {
        }
    }
}
