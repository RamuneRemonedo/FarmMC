package tokyo.ramune.farmmc.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.listener.cursor.CursorClickListener;
import tokyo.ramune.farmmc.listener.cursor.CursorListener;
import tokyo.ramune.farmmc.listener.cursor.CursorMoveListener;
import tokyo.ramune.farmmc.listener.menu.MenuClickListener;
import tokyo.ramune.farmmc.listener.player.FarmPlayerLevelUpListener;
import tokyo.ramune.farmmc.listener.player.FarmPlayerListener;

import java.util.Arrays;

public class ListenerHandler {

    public ListenerHandler() {
    }

    public void registerListeners() {
        Listener[] listeners = Arrays.asList(
                new CursorListener(),
                new FarmPlayerListener(),
                new CursorMoveListener(),
                new CursorClickListener(),
                new FarmPlayerLevelUpListener(),
                new MenuClickListener()
        ).toArray(new Listener[0]);
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, FarmMC.getPlugin());
        }
    }
}