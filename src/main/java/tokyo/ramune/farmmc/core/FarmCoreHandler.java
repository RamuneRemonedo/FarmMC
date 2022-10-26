package tokyo.ramune.farmmc.core;

import org.bukkit.Bukkit;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.database.MySQL;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.listener.ListenerHandler;
import tokyo.ramune.farmmc.core.listener.player.PlayerJoinListener;
import tokyo.ramune.farmmc.core.listener.player.PlayerQuitListener;
import tokyo.ramune.farmmc.core.sidebar.FarmSideBarHandler;
import tokyo.ramune.farmmc.core.subcommand.HelpSubCommand;
import tokyo.ramune.farmmc.core.subcommand.LanguageSubCommand;
import tokyo.ramune.farmmc.core.subcommand.MaintenanceSubCommand;
import tokyo.ramune.farmmc.core.subcommand.ReloadSubCommand;
import tokyo.ramune.farmmc.core.utility.FarmRateLimiter;
import tokyo.ramune.farmmc.game.FarmGameHandler;
import tokyo.ramune.farmmc.maintenance.FarmMaintenanceHandler;

public class FarmCoreHandler implements ModeHandler {
    private static FarmCoreHandler instance;
    private CoreConfig coreConfig;

    public static FarmCoreHandler getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;

        coreConfig = new CoreConfig();
        coreConfig.load();

        connectDatabase();
        FarmLanguageHandler.load();
        FarmLanguageHandler.createTable();
        FarmBossBarHandler.initialize();
        ListenerHandler.registerListeners(
                new PlayerJoinListener(),
                new PlayerQuitListener());
        CommandHandler.registerCommand();
        CommandHandler.registerSubCommands(
                new HelpSubCommand(),
                new LanguageSubCommand(),
                new MaintenanceSubCommand(),
                new ReloadSubCommand());
        CommandHandler.registerTabCompleter();

        FarmSideBarHandler.initialize();

        if (coreConfig.PLUGIN_GAME_MODE)
            FarmMC.registerModeHandler(new FarmGameHandler());

        if (coreConfig.PLUGIN_MAINTENANCE_MODE)
            FarmMC.registerModeHandler(new FarmMaintenanceHandler());
    }

    @Override
    public void onUnload() {
        FarmRateLimiter.removeInstanced();
        MySQL.disconnect();
    }

    private void connectDatabase() {
        getPlugin().getLogger().info("Connecting to database...");
        MySQL.connect();
        if (!MySQL.isConnected()) {
            getPlugin().getLogger().warning("Cannot connect! This plugin require to connect SQLite.");
            Bukkit.shutdown();
        }

        getPlugin().getLogger().info("Connected to database!");
    }

    public CoreConfig getCoreConfig() {
        return coreConfig;
    }
}
