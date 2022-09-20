package tokyo.ramune.farmmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;

public class ListenerHandler {
    private static final Listener[] listeners = {
            new PlayerJoinListener(),
            new PlayerQuitListener(),
            new FarmMenuClickListener()
    };

    public static void registerListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}
