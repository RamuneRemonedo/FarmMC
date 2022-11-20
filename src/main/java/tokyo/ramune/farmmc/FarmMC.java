package tokyo.ramune.farmmc;

import com.rylinaux.plugman.util.PluginUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Notice;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class FarmMC extends JavaPlugin {
    private static FarmMC plugin;
    private static List<ModeHandler> modeHandlers = new ArrayList<>();

    public static FarmMC getPlugin() {
        return plugin;
    }

    public static void registerModeHandler(ModeHandler modeHandler) {
        if (modeHandlers.contains(modeHandler))
            return;

        modeHandlers.add(modeHandler);
        modeHandler.onLoad();
    }

    public static void unregisterAllModeHandler() {
        for (ModeHandler modeHandler : modeHandlers) {
            if (!modeHandlers.contains(modeHandler))
                return;

            modeHandler.onUnload();
        }

        modeHandlers = new ArrayList<>();
    }

    public static List<ModeHandler> getModeHandlers() {
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

    public void reloadScheduled(int seconds) {
        Notice.noticeAutoRestart(seconds);


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
        modeHandlers = new ArrayList<>();

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