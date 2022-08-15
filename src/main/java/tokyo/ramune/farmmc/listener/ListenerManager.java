package tokyo.ramune.farmmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.listener.cursor.CursorClickListener;
import tokyo.ramune.farmmc.listener.cursor.CursorListener;
import tokyo.ramune.farmmc.listener.cursor.CursorMoveListener;
import tokyo.ramune.farmmc.listener.menu.MenuListener;
import tokyo.ramune.farmmc.listener.player.FarmPlayerLevelUpListener;
import tokyo.ramune.farmmc.listener.player.FarmPlayerListener;
import tokyo.ramune.farmmc.listener.player.PlayerListener;

import java.util.Arrays;

public class ListenerManager {

    public ListenerManager() {
    }

    public void registerListeners() {
        Listener[] listeners = Arrays.asList(
                new CursorListener(),
                new FarmPlayerListener(),
                new CursorMoveListener(),
                new FarmPlayerLevelUpListener(),
                new MenuListener(),
                new PlayerListener(),
                new CursorClickListener()
        ).toArray(new Listener[0]);
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}