package tokyo.ramune.farmmc.command;

import com.google.common.util.concurrent.RateLimiter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class CommandRateLimiter {
    private static final Map<CommandSender, RateLimiter> rateLimiter = new HashMap<>();

    public static void initialize() {
        removeAll();

        Bukkit.getOnlinePlayers().forEach(CommandRateLimiter::add);
    }

    public static void add(@Nonnull CommandSender sender) {
        if (isContains(sender))
            return;

        rateLimiter.put(sender, RateLimiter.create(1));
    }

    public static void remove(@Nonnull CommandSender sender) {
        if (!isContains(sender))
            return;

        rateLimiter.remove(sender);
    }

    public static void removeAll() {
        rateLimiter.keySet().forEach(rateLimiter::remove);
    }

    public static boolean tryAcquire(CommandSender sender) {
        sender.sendMessage("\n" + rateLimiter.keySet());

        if (!isContains(sender))
            add(sender);

        return rateLimiter.get(sender).tryAcquire();
    }

    public static boolean isContains(@Nonnull CommandSender sender) {
        return rateLimiter.containsKey(sender);
    }
}
