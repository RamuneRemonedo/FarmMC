package tokyo.ramune.farmmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;

import javax.annotation.Nonnull;

public class Shutdown {
    private int shutdownTimeMax = 60;
    private int shutdownTimeLeft = -1;
    private BukkitTask shutdownTimerTask;

    public Shutdown() {
    }

    public int getShutdownTimeLeft() {
        return shutdownTimeLeft;
    }

    public int getShutdownTimeMax() {
        return shutdownTimeMax;
    }

    public void startShutdownTimer(int seconds) {
        shutdownTimeLeft = seconds;
        shutdownTimeMax = seconds;
        try {
            shutdownTimerTask.cancel();
        } catch (Exception ignored) {
        }

        shutdownTimerTask = Bukkit.getScheduler().runTaskTimer(CoreHandler.getInstance().getPlugin(), () -> {
            if (shutdownTimeLeft < 0) {
                cancelShutdownTimer();
                Bukkit.getServer().shutdown();
            }
            System.out.println(shutdownTimeLeft);
            shutdownTimeLeft--;
        }, 20, 20);
    }

    public void cancelShutdownTimer() {
        try {
            shutdownTimerTask.cancel();
        } catch (Exception ignored) {
        }
        shutdownTimerTask = null;
        shutdownTimeLeft = -1;
    }
}
