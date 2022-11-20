package tokyo.ramune.farmmc.core;

import org.bukkit.Bukkit;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.database.MySQL;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.listener.ListenerHandler;
import tokyo.ramune.farmmc.core.listener.player.PlayerCommandPreprocessListener;
import tokyo.ramune.farmmc.core.listener.player.PlayerJoinListener;
import tokyo.ramune.farmmc.core.listener.player.PlayerQuitListener;
import tokyo.ramune.farmmc.core.listener.player.TabCompleteListener;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.core.sidebar.SideBarHandler;
import tokyo.ramune.farmmc.core.subcommand.*;
import tokyo.ramune.farmmc.core.util.RateLimiter;
import tokyo.ramune.farmmc.game.GameHandler;
import tokyo.ramune.farmmc.maintenance.FarmMaintenanceHandler;

public class CoreHandler implements ModeHandler {
    private static CoreHandler instance;
    private CoreConfig coreConfig;

    public static CoreHandler getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;

        coreConfig = new CoreConfig();
        coreConfig.load();

        connectDatabase();
        LanguageHandler.load();
        LanguageHandler.createTable();
        FarmBossBarHandler.initialize();
        CoreSettingHandler.createTable();
        ListenerHandler.registerListeners(
                new PlayerCommandPreprocessListener(),
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new TabCompleteListener());
        CommandHandler.registerCommand();
        CommandHandler.registerSubCommands(
                new HelpSubCommand(),
                new LanguageSubCommand(),
                new LangSubCommand(),
                new MaintenanceSubCommand(),
                new ReloadSubCommand(),
                new SettingSubCommand(),
                new SQLQuerySubCommand());
        CommandHandler.registerTabCompleter();

        SideBarHandler.initialize();

        if (coreConfig.PLUGIN_GAME_MODE)
            FarmMC.registerModeHandler(new GameHandler());

        if (coreConfig.PLUGIN_MAINTENANCE_MODE)
            FarmMC.registerModeHandler(new FarmMaintenanceHandler());

    }

    @Override
    public void onUnload() {
        RateLimiter.removeInstanced();
        SideBarHandler.removeAll();
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
