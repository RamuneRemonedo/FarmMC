package tokyo.ramune.farmmc.core.listener.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import tokyo.ramune.farmmc.core.CoreHandler;

public class PlayerCommandPreprocessListener implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (player.isOp())
            return;

        String cmd = event.getMessage().replaceFirst("/", "");

        if (cmd.contains(" "))
            cmd = cmd.split(" ")[0];

        if (cmd.contains(":"))
            cmd = cmd.split(":")[1];

        if (cmd.equalsIgnoreCase("plugins")
                || cmd.equalsIgnoreCase("pl")) {
            event.setCancelled(true);
            player.sendMessage("Plugins (1): " + ChatColor.GREEN + "FarmMC-" + CoreHandler.getInstance().getPlugin().getDescription().getVersion());
        }
    }
}
