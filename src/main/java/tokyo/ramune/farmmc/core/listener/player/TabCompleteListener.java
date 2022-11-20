package tokyo.ramune.farmmc.core.listener.player;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class TabCompleteListener implements Listener {
    @EventHandler
    public void onTabComplete(AsyncTabCompleteEvent event) {
        if (!(event.getSender() instanceof Player))
            return;

        String buffer = event.getBuffer().toLowerCase();
        if (buffer.startsWith("/farmmc")
                || buffer.startsWith("/farm"))
            return;

        Player player = (Player) event.getSender();

        if (player.isOp())
            return;

        event.setCompletions(new ArrayList<>());
        event.setCancelled(true);
    }
}
