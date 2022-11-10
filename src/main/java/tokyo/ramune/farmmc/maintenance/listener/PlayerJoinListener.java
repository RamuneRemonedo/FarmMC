package tokyo.ramune.farmmc.maintenance.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.maintenance.bossbar.MaintenanceBossBar;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FarmBossBarHandler.create(new MaintenanceBossBar(player));
    }
}
