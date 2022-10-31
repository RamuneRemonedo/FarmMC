package tokyo.ramune.farmmc.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.core.utility.Permission;

import javax.annotation.Nonnull;

public interface SubCommand {
    default void runCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission().toPermission())) {
            Chat.sendRequirePermission(sender, getPermission());
            return;
        }

        if (isRequirePlayer()
                && !(sender instanceof Player)) {
            Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_REQUIRE_AS_PLAYER), true);
            return;
        }

        try {
            onCommand(sender, args);
        } catch (Exception e) {
            e.printStackTrace();
            Chat.sendMessage(sender,
                    LanguageHandler.getPhase(sender, Phase.COMMAND_AN_ERROR_OCCURRED)
                            .replace("{0}", e.toString())
                    , true);
        }
    }

    @Nonnull
    String getSubCommand();

    String getDescription(CommandSender sender);

    String getHelp(CommandSender sender);

    boolean isRequirePlayer();

    Permission getPermission();

    void onCommand(CommandSender sender, String[] args);
}
