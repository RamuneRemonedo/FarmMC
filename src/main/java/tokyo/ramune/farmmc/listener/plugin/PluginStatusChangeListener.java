package tokyo.ramune.farmmc.listener.plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.event.plugin.PluginStatusChangeEvent;
import tokyo.ramune.farmmc.utility.PluginStatus;

public class PluginStatusChangeListener implements Listener {
    @EventHandler
    public void onPluginStatusChange(PluginStatusChangeEvent event) {
        if (event.getTo().equals(PluginStatus.MAINTENANCE)) {

        }
    }
}
