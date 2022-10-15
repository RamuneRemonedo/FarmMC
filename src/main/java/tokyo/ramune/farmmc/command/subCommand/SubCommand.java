package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.FarmPermission;

import javax.annotation.Nonnull;

public interface SubCommand {
    default void runCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission().toPermission())) {
            Chat.sendRequirePermission(sender, getPermission());
            return;
        }

        if (isRequirePlayer()
                && !(sender instanceof Player)) {
            Chat.sendMessage(sender, Chat.replaceColor("&cYou need to run this command as Player.", '&'), false);
            return;
        }

        try {
            onCommand(sender, args);
        } catch (Exception e) {
            Chat.sendMessage(sender,
                    Chat.replaceColor(
                            "サブコマンド実行中にエラーが発生しました" +
                            "\n&c" + e, '&'
                    ), true);
        }
    }

    @Nonnull
    String getSubCommand();

    String getDescription(CommandSender sender);

    String getHelp(CommandSender sender);

    boolean isRequirePlayer();

    FarmPermission getPermission();

    void onCommand(CommandSender sender, String[] args);
}
