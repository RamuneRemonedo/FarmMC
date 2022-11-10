package tokyo.ramune.farmmc.core.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import javax.annotation.Nonnull;

public class Chat {
    private static final RateLimiter<CommandSender> rateLimiter = new RateLimiter<>(1);

    public static void sendMessage(Player player, String message, boolean addPrefix) {
        if (addPrefix) {
            player.sendMessage(LanguageHandler.getPhase(player, Phase.CHAT_PREFIX) + message);
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendMessage(CommandSender sender, String message, boolean addPrefix) {
        if (addPrefix) {
            sender.sendMessage(LanguageHandler.getPhase(sender, Phase.CHAT_PREFIX) + message);
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

    public static void sendRequirePermission(Player player, Permission permission) {
        player.sendMessage(LanguageHandler.getPhase(player, Phase.CHAT_REQUIRE_PERMISSION)
                .replace("{0}", permission.toPermission().getName())
        );
    }

    public static void sendRequirePermission(CommandSender sender, Permission permission) {
        sender.sendMessage(
                Chat.replaceColor(LanguageHandler.getPhase(sender, Phase.CHAT_REQUIRE_PERMISSION)
                                .replace("{0}", permission.toPermission().getName()
                                ),
                        '&'
                )
        );
    }

    public static String replaceColor(@Nonnull String message, char prefix) {
        return ChatColor.translateAlternateColorCodes(prefix, message);
    }
}