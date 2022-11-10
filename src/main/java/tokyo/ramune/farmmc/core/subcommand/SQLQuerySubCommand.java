package tokyo.ramune.farmmc.core.subcommand;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.core.util.Permission;

public class SQLQuerySubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "query";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SQL_QUERY_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SQL_QUERY_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return false;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_SQL$QUERY;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Chat.sendMessage(sender, getHelp(sender), true);
            return;
        }

        String query = "";

        for (int i = 0; i < (args.length - 1); i++) {
            query += " " + args[i + 1];
        }

        Chat.sendMessage(sender, "Result: " + SQL.query(query, sender), true);
    }
}
