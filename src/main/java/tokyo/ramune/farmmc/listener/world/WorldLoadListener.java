package tokyo.ramune.farmmc.listener.world;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.utility.PluginStatus;
import tokyo.ramune.farmmc.world.FarmWorldHandler;

import java.io.File;

public class WorldLoadListener implements Listener {
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        if (!new File(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME).exists()) {
            FarmWorldHandler.loadTemplate();
            Bukkit.shutdown();
        }

        if (FarmMC.getStatus().equals(PluginStatus.MAINTENANCE)) {
            FarmWorldHandler.loadTemplate();
        }
    }
}
