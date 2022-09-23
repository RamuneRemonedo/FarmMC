package tokyo.ramune.farmmc.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.PluginStatus;
import tokyo.ramune.farmmc.world.WorldHandler;

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
        if (FarmMC.getStatus().equals(PluginStatus.EDIT_TEMPLATE))
            spawnLocation.setWorld(WorldHandler.getTemplateWorld());

        player.teleport(FarmMC.getConfigValue().WORLD_SPAWN_LOCATION);
    }
}
