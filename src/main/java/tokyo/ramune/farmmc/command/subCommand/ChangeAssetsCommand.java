package tokyo.ramune.farmmc.command.subCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.command.SubCommand;
import tokyo.ramune.farmmc.utility.Chat;

public class ChangeAssetsCommand implements SubCommand {
    @NotNull
    @Override
    public String getSubCommand() {
        return "change-assets";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "通常ワールド/アセットワールドに切り替えられます";

    }

    @Override
    public void onCommand(@NotNull Player player, String[] args) {
        if (!player.hasPermission("FarmMC.command.change-world")) {
            Chat.sendRequirePermission(player, "FarmMC.command.change-world");
            return;
        }
        if (player.getWorld().equals(FarmMC.getWorldManager().getAssetsWorld())) {
            player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
            Chat.sendMessage(player, "通常ワールドに移動しました", true);
        } else {
            player.teleport(FarmMC.getWorldManager().getAssetsWorld().getSpawnLocation());
            Chat.sendMessage(player, "アセットワールドに移動しました", true);
        }
    }
}
