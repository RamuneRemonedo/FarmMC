package tokyo.ramune.farmmc.core;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.Shutdown;
import tokyo.ramune.farmmc.core.auth.AuthHandler;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.database.MySQL;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.listener.inventory.InventoryClickListener;
import tokyo.ramune.farmmc.core.listener.inventory.InventoryCloseListener;
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

import java.util.Set;

public class CoreHandler implements ModeHandler {
    private static CoreHandler instance;
    private final Shutdown shutdownHandler = new Shutdown();
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
        AuthHandler.createTable();
        CommandHandler.registerCommand();
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

    @Nullable
    @Override
    public Set<Listener> getListeners() {
        return Set.of(
                new InventoryClickListener(),
                new InventoryCloseListener(),
                new PlayerCommandPreprocessListener(),
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new TabCompleteListener());
    }

    @Nullable
    @Override
    public Set<SubCommand> getSubCommands() {
        return Set.of(
                new HelpSubCommand(),
                new LanguageSubCommand(),
                new LangSubCommand(),
                new MaintenanceSubCommand(),
                new ReloadSubCommand(),
                new SettingSubCommand(),
                new SQLQuerySubCommand(),
                new BanSubCommand(),
                new UnbanSubCommand(),
                new ShutdownSubCommand()
        );
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

    public Shutdown getShutdownHandler() {
        return shutdownHandler;
    }
}
