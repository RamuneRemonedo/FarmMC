package tokyo.ramune.farmmc.game.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.menu.Menu;
import tokyo.ramune.farmmc.core.menu.MenuHandler;
import tokyo.ramune.farmmc.core.util.Permission;
import tokyo.ramune.farmmc.game.cook.CookMenu;

public class CookSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "cook";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return "null";
    }

    @Override
    public String getHelp(CommandSender sender) {
        return "null";
    }

    @Override
    public boolean isRequirePlayer() {
        return true;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_HELP;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Menu menu = new CookMenu(player).getMenu();
        MenuHandler.registerMenu(menu);
        menu.open();
    }
}
