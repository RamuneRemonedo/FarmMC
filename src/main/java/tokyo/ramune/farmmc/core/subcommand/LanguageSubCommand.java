package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.core.utility.FarmPermission;
import tokyo.ramune.farmmc.game.player.PlayerStatus;

import java.util.Arrays;

public class LanguageSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "lang";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_HELP) + Arrays.toString(FarmLanguageHandler.getSupportedLangCodes());
    }

    @Override
    public boolean isRequirePlayer() {
        return true;
    }

    @Override
    public FarmPermission getPermission() {
        return FarmPermission.COMMAND_LANGUAGE;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        if (!FarmLanguageHandler.isSupportedLangCode(args[1])) {
            Chat.sendMessage(sender, FarmLanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_IS_NOT_SUPPORTED), true);
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        Player player = (Player) sender;
        PlayerStatus playerStatus = new PlayerStatus(player);

        playerStatus.setLanguageCode(args[1]);

        Chat.sendMessage(sender, FarmLanguageHandler.getPhase(sender, Phase.COMMAND_LANGUAGE_SUCCESS), true);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
    }
}
