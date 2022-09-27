package tokyo.ramune.farmmc.utility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class Chat {
    public static void sendMessage(Player player, String message, boolean addPrefix) {
        if (addPrefix) {
            player.sendMessage( Chat.replaceColor("&f[&bFramMC&f] &r", '&') + message);
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendMessage(CommandSender sender, String message, boolean addPrefix) {
        if (addPrefix) {
            sender.sendMessage( Chat.replaceColor("&f[&bFramMC&f] &r", '&') + message);
        } else {
            sender.sendMessage(message);
        }
    }

    public static void sendRequirePermission(Player player, FarmPermission permission) {
        player.sendMessage("You need the permission to do this.  " + permission.toPermission().getName());
    }

    public static void sendRequirePermission(CommandSender sender, FarmPermission permission) {
        sender.sendMessage("You need the permission to do this.  " + permission.toPermission().getName());
    }

    public static String replaceColor(@Nonnull String message, char prefix) {
        return ChatColor.translateAlternateColorCodes(prefix, message);
    }
}