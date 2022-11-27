package tokyo.ramune.farmmc;

import com.rylinaux.plugman.util.PluginUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Notice;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class FarmMC extends JavaPlugin {
    private static FarmMC plugin;
    private static Set<ModeHandler> modeHandlers = new HashSet<>();

    public static FarmMC getPlugin() {
        return plugin;
    }

    public static void registerModeHandler(ModeHandler modeHandler) {
        if (modeHandlers.contains(modeHandler))
            return;

        modeHandlers.add(modeHandler);

        if (modeHandler.getListeners() != null)
            modeHandler.getListeners().forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));

        if (modeHandler.getSubCommands() != null)
            modeHandler.getSubCommands().forEach(CommandHandler::registerSubCommands);

        modeHandler.onLoad();
    }

    public static void unregisterAllModeHandler() {
        for (ModeHandler modeHandler : modeHandlers) {
            if (!modeHandlers.contains(modeHandler))
                return;

            if (modeHandler.getListeners() != null)
                modeHandler.getListeners().forEach(HandlerList::unregisterAll);

            if (modeHandler.getSubCommands() != null)
                modeHandler.getSubCommands().forEach(CommandHandler::unregisterSubCommands);


            modeHandler.onUnload();
        }

        modeHandlers = new HashSet<>();
    }

    public static Set<ModeHandler> getModeHandlers() {
        return modeHandlers;
    }

    @Nullable
    public static ModeHandler getModeHandler(Class<? extends ModeHandler> modeHandlerClass) {
        for (ModeHandler modeHandler : modeHandlers) {
            if (modeHandler.getClass().equals(modeHandlerClass))
                return modeHandler;
        }

        return null;
    }

    public void reload() {
        try {
            PluginUtil.reload(this);
        } catch (Exception e) {
            getLogger().warning("Oops... An error occurred while reloading.");
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        modeHandlers = new HashSet<>();

        registerModeHandler(new CoreHandler());

        getLogger().info("The plugin has been enabled.");

        getLogger().info("Modes: ");
        for (ModeHandler modeHandler : modeHandlers) {
            getLogger().info(modeHandler.getClass().getName());
        }
        getLogger().info(" ");
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(
                player -> player.kick(
                        Component.text(
                                LanguageHandler.getPhase(player, Phase.PLUGIN_RESTART)
                        ),
                        PlayerKickEvent.Cause.TIMEOUT
                )
        );

        unregisterAllModeHandler();

        getLogger().info("The plugin has been disabled.");
    }
}