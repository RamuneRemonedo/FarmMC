package tokyo.ramune.farmmc.utility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    public static void sendMessage(Player player, String message, boolean addPrefix) {
        if (addPrefix) {
            player.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_GREEN + "FramMC" + ChatColor.WHITE + "] " + ChatColor.RESET + message);
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendRequirePermission(Player player, FarmPermission permission) {
        player.sendMessage("You need the permission to do this.  " + permission.toPermission().getName());
    }
}