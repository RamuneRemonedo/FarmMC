package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.Language;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.Permission;

import java.util.Arrays;

public class LanguageSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "language";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_HELP) + Arrays.toString(Language.values());
    }

    @Override
    public boolean isRequirePlayer() {
        return true;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_LANGUAGE;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        if (!LanguageHandler.isSupportedLangCode(args[1])) {
            Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_IS_NOT_SUPPORTED), true);
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        Player player = (Player) sender;

        CoreSettingHandler.LANGUAGE.set(player.getUniqueId(), args[1]);

        Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_SUCCESS), true);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
    }
}
