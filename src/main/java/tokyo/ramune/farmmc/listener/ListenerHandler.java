package tokyo.ramune.farmmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.listener.entity.EntityBlockChangeListener;
import tokyo.ramune.farmmc.listener.entity.EntityDamageByEntityListener;
import tokyo.ramune.farmmc.listener.entity.EntityDeathListener;
import tokyo.ramune.farmmc.listener.farm.*;
import tokyo.ramune.farmmc.listener.player.PlayerJoinListener;
import tokyo.ramune.farmmc.listener.player.PlayerLoginListener;
import tokyo.ramune.farmmc.listener.player.PlayerMoveListener;
import tokyo.ramune.farmmc.listener.player.PlayerQuitListener;
import tokyo.ramune.farmmc.listener.plugin.PluginStatusChangeListener;
import tokyo.ramune.farmmc.listener.world.*;

public class ListenerHandler {
    private static final Listener[] listeners = {
            new EntityBlockChangeListener(),
            new EntityDamageByEntityListener(),
            new EntityDeathListener(),

            new FarmCropHarvestListener(),
            new FarmCropPlantListener(),
            new FarmMenuClickListener(),
            new FarmPlayerChangeExpListener(),
            new FarmPlayerLevelUpListener(),

            new PlayerJoinListener(),
            new PlayerLoginListener(),
            new PlayerMoveListener(),
            new PlayerQuitListener(),

            new PluginStatusChangeListener(),

            new BlockBreakListener(),
            new BlockFertilizeListener(),
            new BlockGrowListener(),
            new BlockPlaceListener(),
            new BlockPreDispenseListener(),
            new BlockSpreadListener(),
            new StructureGrowListener()
    };

    public static void registerListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}
