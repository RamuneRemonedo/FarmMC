package tokyo.ramune.farmmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.listener.entity.EntityDamageByEntityListener;
import tokyo.ramune.farmmc.listener.entity.EntityDeathListener;
import tokyo.ramune.farmmc.listener.farm.FarmMenuClickListener;
import tokyo.ramune.farmmc.listener.farm.FarmPlayerLevelUpListener;
import tokyo.ramune.farmmc.listener.player.PlayerJoinListener;
import tokyo.ramune.farmmc.listener.player.PlayerLoginListener;
import tokyo.ramune.farmmc.listener.player.PlayerQuitListener;
import tokyo.ramune.farmmc.listener.plugin.PluginStatusChangeListener;
import tokyo.ramune.farmmc.listener.world.BlockBreakListener;
import tokyo.ramune.farmmc.listener.world.WorldLoadListener;

public class ListenerHandler {
    private static final Listener[] listeners = {
            new PlayerJoinListener(),
            new PlayerQuitListener(),
            new FarmMenuClickListener(),
            new PlayerLoginListener(),
            new BlockBreakListener(),
            new FarmPlayerLevelUpListener(),
            new EntityDamageByEntityListener(),
            new EntityDeathListener(),
            new PluginStatusChangeListener(),
            new WorldLoadListener()
    };

    public static void registerListeners() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}
