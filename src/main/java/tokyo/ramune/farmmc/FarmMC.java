package tokyo.ramune.farmmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.farmmc.config.Config;
import tokyo.ramune.farmmc.database.MySQL;
import tokyo.ramune.farmmc.listener.ListenerHandler;
import tokyo.ramune.farmmc.player.PlayerHandler;
import tokyo.ramune.farmmc.utility.PluginStatus;
import tokyo.ramune.farmmc.world.WorldHandler;

import javax.annotation.Nonnull;

public final class FarmMC extends JavaPlugin {
    private static JavaPlugin plugin;
    private static PluginStatus status = PluginStatus.NORMAL;
    private static Config config;

    @Override
    public void onEnable() {
        plugin = this;

        config = new Config();

        if (status.equals(PluginStatus.NORMAL)) {
            WorldHandler.resetGameWorld();
            connectMySQL();
            PlayerHandler.createTable();
        }

        ListenerHandler.registerListeners();

        getLogger().info("The plugin has been enabled.");
        getLogger().info(ChatColor.RED + "Be careful! This plugin is running under " + status.name() + " mode!");
    }

    @Override
    public void onDisable() {
        WorldHandler.unloadGameWorld();
        WorldHandler.unloadTemplateWorld();
        getLogger().info("The plugin has been disabled.");
    }

    private void connectMySQL() {
        MySQL.connect();
        if (!MySQL.isConnected()) {
            getLogger().warning("Cannot connect to MySQL! This plugin require to connect MySQL.");
            Bukkit.shutdown();
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    @Nonnull
    public static Config getConfigValue() {
        return config;
    }
}