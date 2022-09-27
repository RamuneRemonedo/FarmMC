package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.FarmPermission;
import tokyo.ramune.farmmc.utility.PluginStatus;

public class MaintenanceCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "maintenance";
    }

    @Override
    public String getDescription() {
        return "メンテナンスモードに切り替えます";
    }

    @Override
    public String getHelp() {
        return Chat.replaceColor("&l/farmmc maintenance [true / false] &f&l:  でメンテナンスモードを有効/無効にするかの切り替え", '&');
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
            Chat.sendMessage(sender, getHelp(), true);
            return;
        }

        boolean allow = Boolean.parseBoolean(args[1]);

        if (FarmMC.getStatus().equals(PluginStatus.MAINTENANCE)
                == allow) {
            Chat.sendMessage(sender, "&cすでにその設定になっています。", true);
            return;
        }

        FarmMC.setStatus(
                allow ? PluginStatus.MAINTENANCE : PluginStatus.NORMAL
        );
    }
}
