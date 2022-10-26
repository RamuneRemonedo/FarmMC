package tokyo.ramune.farmmc.maintenance;

import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.listener.ListenerHandler;
import tokyo.ramune.farmmc.maintenance.listener.PlayerJoinListener;
import tokyo.ramune.farmmc.maintenance.listener.PlayerLoginListener;
import tokyo.ramune.farmmc.maintenance.subcommand.ForceSpawnSubCommand;
import tokyo.ramune.farmmc.maintenance.subcommand.SetSpawnSubCommand;

public class FarmMaintenanceHandler implements ModeHandler {
    @Override
    public void onLoad() {
        ListenerHandler.registerListeners(
                new PlayerJoinListener(),
                new PlayerLoginListener());
        CommandHandler.registerSubCommands(
                new ForceSpawnSubCommand(),
                new SetSpawnSubCommand()
        );
    }

    @Override
    public void onUnload() {
    }
}
