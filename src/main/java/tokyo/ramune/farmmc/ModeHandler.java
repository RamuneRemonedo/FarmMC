package tokyo.ramune.farmmc;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.farmmc.core.command.SubCommand;

import javax.annotation.Nullable;
import java.util.Set;

public interface ModeHandler {
    default JavaPlugin getPlugin() {
        return FarmMC.getPlugin();
    }

    void onLoad();

    void onUnload();

    @Nullable
    Set<Listener> getListeners();

    @Nullable
    Set<SubCommand> getSubCommands();
}
