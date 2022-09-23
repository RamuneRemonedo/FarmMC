package tokyo.ramune.farmmc.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.bossbar.MaintenanceFarmBossBar;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.PluginStatus;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Join message
        event.joinMessage(Component.text(ChatColor.GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "+" + ChatColor.GRAY + "] " + ChatColor.GRAY + player.getName() + " joined."));

        // Initialize player database & Update name
        PlayerStatus status = new PlayerStatus(player);
        status.initializeDatabasePlayer();
        status.updateName();

        // Teleport to spawn
        Location spawnLocation = FarmMC.getConfigValue().WORLD_SPAWN_LOCATION;

        Bukkit.getScheduler().runTaskLater(FarmMC.getPlugin(), () -> {
            player.teleport(spawnLocation);
        }, 10);

        // Add Maintenance BossBar
        if (FarmMC.getStatus().equals(PluginStatus.MAINTENANCE))
            FarmBossBarHandler.create(new MaintenanceFarmBossBar(player));
    }
}
