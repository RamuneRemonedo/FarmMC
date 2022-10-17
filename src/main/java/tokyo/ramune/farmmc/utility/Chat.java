package tokyo.ramune.farmmc.utility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;

import javax.annotation.Nonnull;

public class Chat {
    private static final FarmRateLimiter<CommandSender> rateLimiter = new FarmRateLimiter<>(1);

    public static void sendMessage(Player player, String message, boolean addPrefix) {
        if (addPrefix) {
            player.sendMessage(FarmLanguageHandler.getPhase(player, Phase.CHAT_PREFIX) + message);
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendMessage(CommandSender sender, String message, boolean addPrefix) {
        if (addPrefix) {
            sender.sendMessage(FarmLanguageHandler.getPhase(sender, Phase.CHAT_PREFIX) + message);
        } else {
            sender.sendMessage(message);
        }
    }

    public static void sendMessage(Player player, String message, boolean addPrefix, boolean rateLimited) {
        if (rateLimited && !rateLimiter.tryAcquire(player))
            return;

        sendMessage(player, message, addPrefix);
    }

    public static void sendMessage(CommandSender sender, String message, boolean addPrefix, boolean rateLimited) {
        if (rateLimited && !rateLimiter.tryAcquire(sender))
            return;

        sendMessage(sender, message, addPrefix);
    }

    public static void sendRequirePermission(Player player, FarmPermission permission) {
        player.sendMessage(FarmLanguageHandler.getPhase(player, Phase.CHAT_REQUIRE_PERMISSION)
                .replace("{0}", permission.toPermission().getName())
        );
    }

    public static void sendRequirePermission(CommandSender sender, FarmPermission permission) {
        sender.sendMessage(FarmLanguageHandler.getPhase(sender, Phase.CHAT_REQUIRE_PERMISSION)
                .replace("{0}", permission.toPermission().getName())
        );
    }

    public static String replaceColor(@Nonnull String message, char prefix) {
        return ChatColor.translateAlternateColorCodes(prefix, message);
    }
}