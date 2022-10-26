package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.command.CommandSender;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.FarmCoreHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.core.utility.FarmPermission;

import javax.annotation.Nonnull;

public class MaintenanceSubCommand implements SubCommand {
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

        boolean allow = Boolean.parseBoolean(args[1]),
                isCurrent = FarmCoreHandler.getInstance().getCoreConfig().PLUGIN_MAINTENANCE_MODE;

        if (isCurrent == allow) {
            Chat.sendMessage(sender, FarmLanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_ALREADY), true);
            return;
        }

        CoreConfig coreConfig = FarmCoreHandler.getInstance().getCoreConfig();
        coreConfig.getConfig().set("config.PLUGIN_MAINTENANCE_MODE", !isCurrent);
        coreConfig.saveConfig();

        FarmMC.getPlugin().reload();
    }
}
