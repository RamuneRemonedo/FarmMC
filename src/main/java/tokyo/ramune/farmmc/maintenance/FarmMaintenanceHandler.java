package tokyo.ramune.farmmc.maintenance;

import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;
import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.maintenance.listener.PlayerJoinListener;
import tokyo.ramune.farmmc.maintenance.listener.PlayerLoginListener;
import tokyo.ramune.farmmc.maintenance.subcommand.ForceSpawnSubCommand;
import tokyo.ramune.farmmc.maintenance.subcommand.SetSpawnSubCommand;

import java.util.Set;

public class FarmMaintenanceHandler implements ModeHandler {
    @Override
    public void onLoad() {
    }

    @Override
    public void onUnload() {
    }

    @Nullable
    @Override
    public Set<Listener> getListeners() {
        return Set.of(
                new PlayerJoinListener(),
                new PlayerLoginListener());
    }

    @Nullable
    @Override
    public Set<SubCommand> getSubCommands() {
        return Set.of(
                new ForceSpawnSubCommand(),
                new SetSpawnSubCommand()
        );
    }
}
