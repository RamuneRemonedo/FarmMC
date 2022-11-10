package tokyo.ramune.farmmc.maintenance.subcommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.Permission;

public class ForceSpawnSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "force-spawn";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_FORCE$SPAWN;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        boolean allow = Boolean.parseBoolean(args[1]),
                isCurrent = CoreHandler.getInstance().getCoreConfig().FORCE_SPAWN_TELEPORT;

        if (isCurrent == allow) {
            Chat.sendMessage(sender, LanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_ALREADY), true);
            return;
        }

        CoreConfig coreConfig = CoreHandler.getInstance().getCoreConfig();
        coreConfig.getConfig().set("config.FORCE_SPAWN_TELEPORT", allow);
        coreConfig.saveConfig();
        coreConfig.reload();

        Chat.sendMessage(sender,
                LanguageHandler.getPhase(sender, Phase.COMMAND_FORCE_SPAWN_SUCCESS)
                        .replace("{0}", String.valueOf(!isCurrent)),
                true);
    }
}
