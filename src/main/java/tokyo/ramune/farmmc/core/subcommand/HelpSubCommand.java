package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.setting.SettingsMenu;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.Permission;

public class HelpSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "help";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_HELP_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_HELP_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_HELP;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        new SettingsMenu((Player) sender).getMenu().open();

        String help =
                LanguageHandler.getPhase(sender, Phase.COMMAND_HELP_LIST)
                        .replace("{0}", getHelp(sender));

        for (SubCommand subCommand : CommandHandler.getSubCommands()) {
            help += "&f&l" + subCommand.getSubCommand() + " :  &b" + subCommand.getDescription(sender) + "\n";
        }

        Chat.sendMessage(sender,
                Chat.replaceColor(help, '&'),
                true
        );
    }
}
