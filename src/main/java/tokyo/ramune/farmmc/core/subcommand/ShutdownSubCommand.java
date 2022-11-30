package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Permission;

public class ShutdownSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "shutdown";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SHUTDOWN_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SHUTDOWN_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_SHUTDOWN;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        try {
            if (args[1].equalsIgnoreCase("now"))
                Bukkit.shutdown();
        } catch (Exception e) {
            CoreHandler.getInstance().getShutdownHandler().startShutdownTimer(60);
        }
    }
}
