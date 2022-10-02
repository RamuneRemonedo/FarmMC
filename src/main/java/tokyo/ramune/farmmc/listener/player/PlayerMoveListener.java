package tokyo.ramune.farmmc.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("Temperature: " + Math.round(player.getLocation().getBlock().getTemperature() * 10.0) / 10.0);
    }
}
