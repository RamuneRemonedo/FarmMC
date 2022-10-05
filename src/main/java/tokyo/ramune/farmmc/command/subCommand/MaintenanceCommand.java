package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.command.CommandSender;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.FarmPermission;
import tokyo.ramune.farmmc.utility.PluginStatus;

import javax.annotation.Nonnull;

public class MaintenanceCommand implements SubCommand {
    @Nonnull
    @Override
    public String getSubCommand() {
        return "maintenance";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public FarmPermission getPermission() {
        return FarmPermission.COMMAND_MAINTENANCE;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        boolean allow = Boolean.parseBoolean(args[1]);

        if (FarmMC.getStatus().equals(PluginStatus.MAINTENANCE)
                == allow) {
            Chat.sendMessage(sender, FarmLanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_ALREADY), true);
            return;
        }

        FarmMC.setStatus(
                allow ? PluginStatus.MAINTENANCE : PluginStatus.NORMAL
        );
    }
}
