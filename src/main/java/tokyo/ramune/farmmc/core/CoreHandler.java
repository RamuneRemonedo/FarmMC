package tokyo.ramune.farmmc.core;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.ShutdownHandler;
import tokyo.ramune.farmmc.core.auth.AuthHandler;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.database.MySQL;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.listener.inventory.InventoryClickListener;
import tokyo.ramune.farmmc.core.listener.inventory.InventoryCloseListener;
import tokyo.ramune.farmmc.core.listener.player.*;
import tokyo.ramune.farmmc.core.menu.MenuHandler;
import tokyo.ramune.farmmc.core.menu.ServerMenu;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.core.sidebar.SideBarHandler;
import tokyo.ramune.farmmc.core.subcommand.*;
import tokyo.ramune.farmmc.core.util.RateLimiter;
import tokyo.ramune.farmmc.game.GameHandler;
import tokyo.ramune.farmmc.maintenance.FarmMaintenanceHandler;

import javax.annotation.Nullable;
import java.util.Set;

public class CoreHandler implements ModeHandler {
    private static CoreHandler instance;
    private final ShutdownHandler shutdownHandler = new ShutdownHandler();
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
        MenuHandler.registerGlow();
        CommandHandler.registerCommand();
        CommandHandler.registerTabCompleter();
        ServerMenu.initialize();
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
                new TabCompleteListener(),
                new PlayerInteractListener(),
                new PlayerDropItemListener());
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

    public ShutdownHandler getShutdownHandler() {
        return shutdownHandler;
    }
}
