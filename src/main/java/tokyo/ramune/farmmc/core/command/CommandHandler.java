package tokyo.ramune.farmmc.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.subcommand.HelpSubCommand;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.RateLimiter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler {
    public static final RateLimiter<CommandSender> rateLimiter = new RateLimiter<>(1);
    private static Set<SubCommand> subCommands = new HashSet<>();

    public static RateLimiter<CommandSender> getRateLimiter() {
        return rateLimiter;
    }

    public static void initialize() {
        subCommands = new HashSet<>();
    }

    public static void registerCommand() {
        Objects.requireNonNull(FarmMC.getPlugin().getCommand("farmmc")).setExecutor(new CommandExecutor());
    }

    public static void registerTabCompleter() {
        Objects.requireNonNull(FarmMC.getPlugin().getCommand("farmmc")).setTabCompleter(new TabCompleter());
    }

    public static void registerSubCommands(SubCommand... subCommands) {
        CommandHandler.subCommands.addAll(Arrays.asList(subCommands));
    }

    private static boolean isRegistered(SubCommand subCommand) {
        for (SubCommand registered : subCommands) {
            if (registered.getSubCommand().equals(subCommand.getSubCommand()))
                return true;
        }

        return false;
    }

    public static void unregisterSubCommands(SubCommand... registeredSubCommands) {
        for (SubCommand subCommand : registeredSubCommands) {
            if (isRegistered(subCommand))
                subCommands.remove(subCommand);
        }
    }

    public static void unregisterAllSubCommands() {
        subCommands = new HashSet<>();
    }

    public static Set<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Nullable
    public static SubCommand getSubCommand(@Nonnull String subCommand) {
        try {
            return subCommands.stream()
                    .filter(subCommand1 -> subCommand1.getSubCommand().equals(subCommand))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            return null;
        }
    }
}

class CommandExecutor implements org.bukkit.command.CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        if (!CommandHandler.getRateLimiter().tryAcquire(commandSender)) {
            Chat.sendMessage(commandSender, LanguageHandler.getPhase(commandSender, Phase.COMMAND_RATE_LIMIT), true);
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

        Chat.sendMessage(commandSender, LanguageHandler.getPhase(commandSender, Phase.COMMAND_NOT_FOUND), true);
        new HelpSubCommand().runCommand(commandSender, args);

        return true;
    }
}

class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand _command : CommandHandler.getSubCommands()) {
                if (!sender.hasPermission(_command.getPermission().toPermission()))
                    continue;

                StringUtil.copyPartialMatches(args[0], Collections.singleton(_command.getSubCommand()), completions);
            }

            Collections.sort(completions);

            return completions;
        }
        return null;
    }
}