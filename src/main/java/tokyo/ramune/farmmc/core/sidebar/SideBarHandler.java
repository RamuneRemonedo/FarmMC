package tokyo.ramune.farmmc.core.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SideBarHandler {
    private static Set<SideBar> sideBars = new HashSet<>();
    private static BukkitTask updateTimer;

    public static void initialize() {
        sideBars = new HashSet<>();
        startUpdateTimer();
    }

    public static void startUpdateTimer() {
        if (updateTimer == null || updateTimer.isCancelled())
            updateTimer = Bukkit.getScheduler().runTaskTimerAsynchronously(
                    CoreHandler.getInstance().getPlugin(),
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

    public static Set<SideBar> getSideBars() {
        return sideBars;
    }

    public static void setSideBar(SideBar instance) {
        if (sideBars.contains(instance))
            return;

        sideBars.add(instance);
    }

    public static void updateVisible(@Nonnull Player player) {
        SideBar sideBar = getCurrentSideBar(player);

        if (sideBar == null)
            return;

        if (CoreSettingHandler.SIDEBAR_ENABLE.getData(player.getUniqueId()).getAsBoolean()) {
            sideBar.show();
        } else {
            sideBar.hide();
        }
    }

    private static void updateAll() {
        sideBars.forEach(SideBar::update);
    }

    public static void remove(Player player) {
        sideBars.removeIf(sideBar -> sideBar.getPlayer().equals(player));
    }

    public static void removeAll() {
        sideBars = new HashSet<>();
    }
}
