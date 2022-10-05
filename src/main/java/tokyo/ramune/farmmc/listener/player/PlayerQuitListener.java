package tokyo.ramune.farmmc.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.farmmc.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.utility.Notice;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Quit message
        Notice.noticeQuitMessage(player);

        // Remove boss bars
        FarmBossBarHandler.remove(FarmBossBarHandler.getBossBar(player));
    }
}
