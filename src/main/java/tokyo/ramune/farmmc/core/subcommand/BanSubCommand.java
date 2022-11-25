package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.auth.AuthHandler;
import tokyo.ramune.farmmc.core.auth.PlayerProfile;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.Permission;

import java.util.UUID;

public class BanSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "ban";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_BAN_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_BAN_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_BAN;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 3) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);
        try {
            targetPlayer = Bukkit.getPlayer(UUID.fromString(args[1]));
        } catch (Exception ignored) {
        }
        if (targetPlayer == null) {
            Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_BAN_PLAYER_NOT_FOUND), true);
            return;
        }
        PlayerProfile playerProfile = AuthHandler.get(targetPlayer.getUniqueId());

        if (playerProfile.isBanned()) {
            Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_BAN_ALREADY_BANNED), true);
            return;
        }

        playerProfile
                .ban(args[2])
                .apply();
        Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_BAN_SUCCESS), true);
    }
}
