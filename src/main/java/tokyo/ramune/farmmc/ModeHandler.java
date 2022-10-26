package tokyo.ramune.farmmc;

import org.bukkit.plugin.java.JavaPlugin;

public interface ModeHandler {
    default JavaPlugin getPlugin() {
        return FarmMC.getPlugin();
    }

    void onLoad();

    void onUnload();
}
