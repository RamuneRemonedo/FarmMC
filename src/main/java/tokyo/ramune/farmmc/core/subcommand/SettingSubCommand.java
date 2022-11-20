package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.setting.SettingsMenu;
import tokyo.ramune.farmmc.core.util.Permission;

public class SettingSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "setting";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SETTING_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SETTING_DESCRIPTION);
    }

    @Override
    public boolean isRequirePlayer() {
        return true;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_SETTING;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        SettingsMenu menu = new SettingsMenu(player);

        menu.getMenu().register();
        menu.getMenu().open();
    }
}
