package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.command.CommandSender;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.Permission;

import javax.annotation.Nonnull;

public class MaintenanceSubCommand implements SubCommand {
    @Nonnull
    @Override
    public String getSubCommand() {
        return "maintenance";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_MAINTENANCE;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        boolean allow = Boolean.parseBoolean(args[1]),
                isCurrent = CoreHandler.getInstance().getCoreConfig().PLUGIN_MAINTENANCE_MODE;

        if (isCurrent == allow) {
            Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_MAINTENANCE_ALREADY), true);
            return;
        }

        CoreConfig coreConfig = CoreHandler.getInstance().getCoreConfig();
        coreConfig.getConfig().set("config.PLUGIN_MAINTENANCE_MODE", !isCurrent);
        coreConfig.saveConfig();

        FarmMC.getPlugin().reload();
    }
}
