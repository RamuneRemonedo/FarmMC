package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.command.SubCommand;
import tokyo.ramune.farmmc.utility.Chat;

public class StuckCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "stuck";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "立ち往生したときに救済します";
    }

    @Override
    public void onCommand(@NotNull Player player, String[] args) {
        if (!player.hasPermission("FarmMC.command.stuck")) {
            Chat.sendRequirePermission(player, "FarmMC.command.stuck");
            return;
        }
        FarmMC.getFarmPlayerManager().stuckTeleport(player);
    }
}
