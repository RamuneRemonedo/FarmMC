package tokyo.ramune.farmmc;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.farmmc.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.command.CommandHandler;
import tokyo.ramune.farmmc.config.Config;
import tokyo.ramune.farmmc.database.MySQL;
import tokyo.ramune.farmmc.event.plugin.PluginStatusChangeEvent;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.listener.ListenerHandler;
import tokyo.ramune.farmmc.player.PlayerHandler;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.PluginStatus;

import javax.annotation.Nonnull;

public final class FarmMC extends JavaPlugin {
    private static JavaPlugin plugin;
    private static PluginStatus status = PluginStatus.NORMAL;
    private static Config config;

    @Override
    public void onEnable() {
        plugin = this;

        config = new Config();
        config.load();

        FarmLanguageHandler.load();

        connectMySQL();
        PlayerHandler.initialize();

        FarmBossBarHandler.initialize();

        ListenerHandler.registerListeners();

        CommandHandler.registerCommand();
        CommandHandler.registerSubCommands();
        CommandHandler.registerTabCompleter();

        getLogger().info("The plugin has been enabled.");
        getLogger().info(
                Chat.replaceColor(
                        "&cBe careful! This plugin is running under " + status.name().toLowerCase() + " mode!",
                        '&'
                )
        );
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(
                player -> player.kick(
                        Component.text(
                                FarmLanguageHandler.getPhase(player, Phase.PLUGIN_RESTART)
                        ),
                        PlayerKickEvent.Cause.TIMEOUT
                )
        );

        getLogger().info("The plugin has been disabled.");
    }

    private void connectMySQL() {
        getLogger().info("Connecting to MySQL server");
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

    public static PluginStatus getStatus() {
        return status;
    }

    public static void setStatus(PluginStatus status) {
        if (FarmMC.status.equals(status))
            return;

        PluginStatusChangeEvent event = new PluginStatusChangeEvent(FarmMC.status, status);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled())
            return;

        FarmMC.status = status;
    }
}