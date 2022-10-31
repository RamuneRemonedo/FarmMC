package tokyo.ramune.farmmc.core.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.core.FarmCoreHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SideBarHandler {
    private static List<SideBar> sideBars = new ArrayList<>();
    private static BukkitTask updateTimer;

    public static void initialize() {
        sideBars = new ArrayList<>();
        startUpdateTimer();
    }

    public static void startUpdateTimer() {
        if (updateTimer == null || updateTimer.isCancelled())
            updateTimer = Bukkit.getScheduler().runTaskTimerAsynchronously(
                    FarmCoreHandler.getInstance().getPlugin(),
                    SideBarHandler::updateAll,
                    20, 5
            );
    }

    @Nullable
    public static SideBar getCurrentSideBar(Player player) {
        try {
            return sideBars.stream()
                    .filter(sidebar -> sidebar.getPlayer().equals(player))
                    .collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static List<SideBar> getSideBars() {
        return sideBars;
    }

    public static void setSideBar(SideBar instance) {
        if (sideBars.contains(instance))
            return;

        sideBars.add(instance);
    }

    public static void updateAll() {
        sideBars.forEach(SideBar::update);
    }

    public static void remove(Player player) {
        sideBars.removeIf(sideBar -> sideBar.getPlayer().equals(player));
    }

    public static void removeAll() {
        sideBars = new ArrayList<>();
    }
}
