package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.command.CommandHandler;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.FarmPermission;

public class HelpSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "help";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_HELP_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_HELP_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public FarmPermission getPermission() {
        return FarmPermission.COMMAND_HELP;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        String help =
                FarmLanguageHandler.getPhase(sender, Phase.COMMAND_HELP_LIST)
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
