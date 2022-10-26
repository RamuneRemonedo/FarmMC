package tokyo.ramune.farmmc.maintenance.subcommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.FarmCoreHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.core.utility.FarmPermission;

public class ForceSpawnSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "force-spawn";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return FarmLanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public FarmPermission getPermission() {
        return FarmPermission.COMMAND_FORCE$SPAWN;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        boolean allow = Boolean.parseBoolean(args[1]),
                isCurrent = FarmCoreHandler.getInstance().getCoreConfig().FORCE_SPAWN_TELEPORT;

        if (isCurrent == allow) {
            Chat.sendMessage(sender, FarmLanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_ALREADY), true);
            return;
        }

        CoreConfig coreConfig = FarmCoreHandler.getInstance().getCoreConfig();
        coreConfig.getConfig().set("config.FORCE_SPAWN_TELEPORT", allow);
        coreConfig.saveConfig();
        coreConfig.reload();

        Chat.sendMessage(sender,
                FarmLanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_SUCCESS)
                        .replace("{0}", String.valueOf(!isCurrent)),
                true);
    }
}
