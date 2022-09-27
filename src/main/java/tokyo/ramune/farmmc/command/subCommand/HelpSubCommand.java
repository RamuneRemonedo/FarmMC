package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.command.CommandHandler;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.FarmPermission;

public class HelpSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "このヘルプを表示します";
    }

    @Override
    public String getHelp() {
        return Chat.replaceColor("&l/farmmc help [サブコマンド] &f&l:  でヘルプを表示", '&');
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

        String help = "\n&l[&b&lコマンド ヘルプ&f&l]" +
                "\n" + getHelp() +
                "\n\n" +
                "\n&f&l[&d&lサブコマンド 一覧&f&l]" +
                "\n";

        for (SubCommand subCommand : CommandHandler.getSubCommands()) {
            help += "&f&l" + subCommand.getSubCommand() + " :  " +subCommand.getDescription() + "\n";
        }

        Chat.sendMessage(sender,
                Chat.replaceColor(help, '&'),
                true
        );
    }
}
