package tokyo.ramune.farmmc.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;

public class ListenerHandler {
    public static void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}
