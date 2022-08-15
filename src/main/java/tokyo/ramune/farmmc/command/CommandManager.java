package tokyo.ramune.farmmc.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.command.subCommand.ChangeAssetsCommand;
import tokyo.ramune.farmmc.command.subCommand.HelpCommand;
import tokyo.ramune.farmmc.command.subCommand.StuckCommand;
import tokyo.ramune.farmmc.utility.Chat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class CommandManager {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>(Arrays.asList(
            new HelpCommand(),
            new ChangeAssetsCommand(),
            new StuckCommand()
    ));

    public CommandManager() {
        Objects.requireNonNull(FarmMC.getPlugin().getCommand("farmmc")).setExecutor(this::onCommand);
        Objects.requireNonNull(FarmMC.getPlugin().getCommand("farmmc")).setTabCompleter(this::onTabComplete);
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Nullable
    public SubCommand getSubCommand(String subCommand) {
        for (SubCommand command : subCommands) {
            if (command.getSubCommand().equals(subCommand)) return command;
        }
        return null;
    }

    private boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            Objects.requireNonNull(getSubCommand("help")).onCommand(player, args);
            return true;
        }
        try {
            Objects.requireNonNull(getSubCommand(args[0])).onCommand(player, args);
        } catch (Exception e) {
            Chat.sendMessage(player, ChatColor.RED + "サブコマンドが見つかりませんでした... もしくは、サブコマンド実行中にエラーが発生しました", true);
            Objects.requireNonNull(getSubCommand("help")).onCommand(player, args);
        }
        return true;
    }


    private @Nullable List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand _command : getSubCommands()) {
                StringUtil.copyPartialMatches(args[0], Collections.singleton(_command.getSubCommand()), completions);
            }
            Collections.sort(completions);
            return completions;
        }
        return null;
    }
}
