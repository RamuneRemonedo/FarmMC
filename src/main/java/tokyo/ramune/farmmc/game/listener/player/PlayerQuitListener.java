package tokyo.ramune.farmmc.game.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.sidebar.SideBarHandler;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    }
}
