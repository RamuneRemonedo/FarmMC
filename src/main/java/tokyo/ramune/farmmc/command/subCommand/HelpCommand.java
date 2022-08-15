package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.command.SubCommand;
import tokyo.ramune.farmmc.utility.Chat;

public class HelpCommand implements SubCommand {

    @NonNull
    @Override
    public String getSubCommand() {
        return "help";
    }

    @NonNull
    @Override
    public String getDescription() {
        return "ヘルプを表示します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.hasPermission("FarmMC.command.help")) {
            Chat.sendRequirePermission(player, "FarmMC.command.help");
            return;
        }
        Chat.sendMessage(player, ChatColor.GREEN.toString(), true);
        Chat.sendMessage(player, ChatColor.GREEN + "[ヘルプ]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "[サブコマンド] " + ChatColor.LIGHT_PURPLE + "[説明]", true);
        for (SubCommand command : FarmMC.getCommandManager().getSubCommands()) {
            Chat.sendMessage(player, ChatColor.AQUA + ChatColor.BOLD.toString() + command.getSubCommand() + "   " + ChatColor.LIGHT_PURPLE + command.getDescription(), true);
        }
    }
}