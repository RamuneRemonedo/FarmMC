package tokyo.ramune.farmmc.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.farmmc.bossbar.FarmBossBarHandler;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Quit message
        event.quitMessage(Component.text(ChatColor.GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "-" + ChatColor.GRAY + "] " + ChatColor.GRAY + player.getName() + " left."));

        // Remove boss bars
        FarmBossBarHandler.remove(FarmBossBarHandler.getBossBar(player));
    }
}
