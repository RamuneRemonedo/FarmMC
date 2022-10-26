package tokyo.ramune.farmmc.core.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.core.FarmCoreHandler;

import java.util.ArrayList;
import java.util.List;

public class FarmSideBarHandler {
    private static List<FarmSideBar> sideBars = new ArrayList<>();
    private static BukkitTask updateTimer;

    public static void initialize() {
        sideBars = new ArrayList<>();
        startUpdateTimer();
    }

    public static void startUpdateTimer() {
        if (updateTimer == null || updateTimer.isCancelled())
            updateTimer = Bukkit.getScheduler().runTaskTimerAsynchronously(
                    FarmCoreHandler.getInstance().getPlugin(),
                    FarmSideBarHandler::updateAll,
                    20, 5
            );
    }

    public static void registerSideBar(FarmSideBar instance) {
        if (sideBars.contains(instance))
            return;

        sideBars.add(instance);
    }

    public static void updateAll() {
        sideBars.forEach(FarmSideBar::update);
    }

    public static void remove(Player player) {
        sideBars.forEach(sideBar -> {
            if (sideBar.getPlayer().equals(player))
                sideBars.remove(sideBar);
        });
    }
}
