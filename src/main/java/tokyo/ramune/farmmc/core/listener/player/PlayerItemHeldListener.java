package tokyo.ramune.farmmc.core.listener.player;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldListener implements Listener {
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        player.playSound(location, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 0.3F, 2);
    }
}
