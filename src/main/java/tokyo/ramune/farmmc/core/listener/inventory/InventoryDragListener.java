package tokyo.ramune.farmmc.core.listener.inventory;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        Location location = player.getLocation();

        player.playSound(location, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 0.3F, 2);
    }
}
