package tokyo.ramune.farmmc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import tokyo.ramune.farmmc.world.WorldHandler;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().equals(WorldHandler.getGameWorld()))
            return;


    }
}
