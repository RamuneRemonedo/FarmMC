package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Permission;

public class ReloadSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "reload";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_RELOAD_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_RELOAD_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_RELOAD;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        FarmMC.getPlugin().reload();
    }
}
