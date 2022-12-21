package tokyo.ramune.farmmc.core.listener.player;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsListener implements Listener {
    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        player.playSound(location, Sound.ENTITY_BAT_TAKEOFF, 0.5F, 1F);
    }
}
