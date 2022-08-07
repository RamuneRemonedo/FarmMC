package tokyo.ramune.farmmc;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.farmmc.bossbar.BossBarManager;
import tokyo.ramune.farmmc.config.Config;
import tokyo.ramune.farmmc.cursor.CursorManager;
import tokyo.ramune.farmmc.database.DatabaseManager;
import tokyo.ramune.farmmc.listener.ListenerHandler;
import tokyo.ramune.farmmc.menu.MenuManager;
import tokyo.ramune.farmmc.player.FarmPlayerManager;
import tokyo.ramune.farmmc.world.WorldManager;

public final class FarmMC extends JavaPlugin {

    private static JavaPlugin plugin;

    private static Config config;
    private static WorldManager worldManager;
    private static DatabaseManager databaseManager;

    private static FarmPlayerManager farmPlayerManager;
    private static BossBarManager bossBarManager;
    private static CursorManager cursorManager;

    private static MenuManager menuManager;

    @Override
    public void onEnable() {
        plugin = this;

        config = new Config();
        worldManager = new WorldManager();
        worldManager.loadAssetsWorld();
        databaseManager = new DatabaseManager();
        databaseManager.createTables();

        farmPlayerManager = new FarmPlayerManager();
        bossBarManager = new BossBarManager();
        cursorManager = new CursorManager();

        menuManager = new MenuManager();

        new ListenerHandler().registerListeners();
        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        cursorManager.removeAllCursor();
        getLogger().info("The plugin has been disabled.");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static Config getConfigFile() {
        return config;
    }

    public static WorldManager getWorldManager() {
        return worldManager;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static CursorManager getCursorManager() {
        return cursorManager;
    }

    public static FarmPlayerManager getFarmPlayerManager() {
        return farmPlayerManager;
    }

    public static BossBarManager getBossBarManager() {
        return bossBarManager;
    }

    public static MenuManager getMenuManager() {
        return menuManager;
    }
}
