package tokyo.ramune.farmmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.listener.entity.EntityBlockChangeListener;
import tokyo.ramune.farmmc.listener.entity.EntityDamageByEntityListener;
import tokyo.ramune.farmmc.listener.entity.EntityDeathListener;
import tokyo.ramune.farmmc.listener.farm.*;
import tokyo.ramune.farmmc.listener.player.*;
import tokyo.ramune.farmmc.listener.plugin.PluginStatusChangeListener;
import tokyo.ramune.farmmc.listener.world.*;

public class ListenerHandler {
    private static final Listener[] listeners = {
            new PlayerJoinListener(),
            new PlayerQuitListener(),
            new FarmMenuClickListener(),
            new PlayerLoginListener(),
            new FarmPlayerLevelUpListener(),
            new EntityDamageByEntityListener(),
            new EntityDeathListener(),
            new PluginStatusChangeListener(),
            new PlayerMoveListener(),
            new BlockBreakListener(),
            new BlockPreDispenseListener(),
            new BlockGrowListener(),
            new BlockFertilizeListener(),
            new EntityBlockChangeListener(),
            new FarmPlayerChangeExpListener(),
            new BlockPlaceListener(),
            new FarmCropPlantListener(),
            new FarmCropHarvestListener()
    };

    public static void registerListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}
