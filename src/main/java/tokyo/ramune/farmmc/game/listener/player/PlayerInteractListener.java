package tokyo.ramune.farmmc.game.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import tokyo.ramune.farmmc.game.crop.FarmServantEntity;

public class PlayerInteractListener implements Listener {
    FarmServantEntity servant;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction().isLeftClick()) {
            if (servant == null)
                servant = new FarmServantEntity(player.getLocation());
            servant.runSpeakAnimation("Hello! I love you, master~", player.getLocation());
        }
    }
}
