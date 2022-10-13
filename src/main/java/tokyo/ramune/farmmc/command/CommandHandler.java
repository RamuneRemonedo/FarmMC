package tokyo.ramune.farmmc.command;

import com.google.common.util.concurrent.RateLimiter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.command.subCommand.HelpSubCommand;
import tokyo.ramune.farmmc.command.subCommand.LanguageSubCommand;
import tokyo.ramune.farmmc.command.subCommand.MaintenanceCommand;
import tokyo.ramune.farmmc.command.subCommand.SubCommand;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommandHandler {
    private static List<SubCommand> subCommands;
    private static final RateLimiter rateLimiter = RateLimiter.create(3);

    public static void registerCommand() {
        Objects.requireNonNull(FarmMC.getPlugin().getCommand("farmmc")).setExecutor(new FarmCommandExecutor());
    }

    public static void registerTabCompleter() {
        Objects.requireNonNull(FarmMC.getPlugin().getCommand("farmmc")).setTabCompleter(new FarmTabCompleter());
    }

    public static void registerSubCommands() {
        subCommands = new ArrayList<>();
        subCommands.addAll(new ArrayList<>() {{
            add(new HelpSubCommand());
            add(new MaintenanceCommand());
            add(new LanguageSubCommand());
        }});
    }

    public static List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public static RateLimiter getRateLimiter() {
        return rateLimiter;
    }
}

class FarmCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        if (!CommandHandler.getRateLimiter().tryAcquire()) {
            Chat.sendMessage(commandSender, FarmLanguageHandler.getPhase(commandSender, Phase.COMMAND_RATE_LIMIT), true);
            return true;
        }

        if (args.length == 0) {
            new HelpSubCommand().runCommand(commandSender, args);
            return true;
        }

        for (SubCommand subCommand : CommandHandler.getSubCommands()) {
            if (subCommand.getSubCommand().equalsIgnoreCase(args[0])) {
                subCommand.runCommand(commandSender, args);
                return true;
            }
        }

        Chat.sendMessage(commandSender, FarmLanguageHandler.getPhase(commandSender, Phase.COMMAND_NOT_FOUND), true);
        new HelpSubCommand().runCommand(commandSender, args);

        return true;
    }
}

class FarmTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand _command : CommandHandler.getSubCommands()) {
                StringUtil.copyPartialMatches(args[0], Collections.singleton(_command.getSubCommand()), completions);
            }
            Collections.sort(completions);
            return completions;
        }
        return null;
    }
}