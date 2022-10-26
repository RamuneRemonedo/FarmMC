package tokyo.ramune.farmmc.core.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.FarmMC;

import javax.annotation.Nonnull;

public class AutoHide {
    private final FarmBossBar farmBossBar;
    private boolean isAutoHide = false;
    private BukkitTask autoHideTask;

    public AutoHide(@Nonnull FarmBossBar farmBossBar) {
        this.farmBossBar = farmBossBar;
    }

    public boolean isAutoHide() {
        return isAutoHide;
    }

    public void setAutoHide(boolean autoHide) {
        isAutoHide = autoHide;
    }

    public void update() {
        if (!isAutoHide)
            return;

        if (autoHideTask != null)
            autoHideTask.cancel();

        farmBossBar.getBossBar().setVisible(true);
        autoHideTask = Bukkit.getScheduler().runTaskLaterAsynchronously(FarmMC.getPlugin(), () -> {
            if (isAutoHide) farmBossBar.getBossBar().setVisible(false);
        }, 60);
    }
}
