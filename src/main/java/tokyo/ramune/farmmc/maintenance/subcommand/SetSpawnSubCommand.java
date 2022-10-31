package tokyo.ramune.farmmc.maintenance.subcommand;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.core.FarmCoreHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.core.utility.Permission;

public class SetSpawnSubCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "set-spawn";
    }

    @Override
    public String getDescription(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SPAWN_DESCRIPTION);
    }

    @Override
    public String getHelp(CommandSender sender) {
        return LanguageHandler.getPhase(sender, Phase.COMMAND_SPAWN_HELP);
    }

    @Override
    public boolean isRequirePlayer() {
        return true;
    }

    @Override
    public Permission getPermission() {
        return Permission.COMMAND_SPAWN;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location fixedLocation = new Location(player.getWorld(), 0, 0, 0, 0, 0);

        fixedLocation.set(player.getLocation().getBlockX() + 0.5
                , player.getLocation().getBlockY()
                , player.getLocation().getBlockZ() + 0.5);
        fixedLocation.setYaw(player.getLocation().getYaw());
        fixedLocation.setPitch(0);

        CoreConfig coreConfig = FarmCoreHandler.getInstance().getCoreConfig();

        coreConfig.setLocation("config.SPAWN_LOCATION", fixedLocation);
        coreConfig.saveConfig();
        coreConfig.reload();

        String locationStr =
                Chat.replaceColor(
                        "&f&lx: &f" + fixedLocation.getX() + " &ly: &f" + fixedLocation.getY() + " &lz: &f" + fixedLocation.getZ(),
                        '&');

        Chat.sendMessage(
                player,
                LanguageHandler.getPhase(player, Phase.COMMAND_SPAWN_SUCCESS)
                        .replace("{0}", locationStr),
                true);
    }
}
